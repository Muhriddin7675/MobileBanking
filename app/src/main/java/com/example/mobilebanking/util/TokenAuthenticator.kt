package com.example.mobilebanking.util

import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.data.remote.api.AddCardApi
import com.example.mobilebanking.data.remote.request.UpdateTokenRequest
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Provider

class TokenAuthenticator(
    private val pref: MyShared,
    private val cardApi: Provider<AddCardApi>
) : Interceptor {
    private fun refreshToken(chain: Interceptor.Chain, request: Request): Response {
        val response = cardApi.get().refreshToken(UpdateTokenRequest(pref.getRefreshToken())).execute()
        if (response.isSuccessful) {
            val access = response.body()?.accessToken
            val refresh = response.body()?.refreshToken
            pref.setRefreshToken(refresh.toString())
            pref.setAccessToken(access.toString())

            val newRequest = request.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", "Bearer $access")
                .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(request)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = pref.getAccessToken()
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = chain.proceed(request)

        if (response.code == 401) {
            response.close()
            return refreshToken(chain, request)
        }
        return response
    }

}