package com.example.mobilebanking.data.remote.api

import com.example.mobilebanking.data.remote.request.SignInUserDataRequest
import com.example.mobilebanking.data.remote.request.SignInVerifyRequest
import com.example.mobilebanking.data.remote.request.SignUpUserDataRequest
import com.example.mobilebanking.data.remote.request.SignUpVerifyRequest
import com.example.mobilebanking.data.remote.request.UpdateTokenRequest
import com.example.mobilebanking.data.remote.response.AccessInRefreshTokenResponse
import com.example.mobilebanking.data.remote.response.MessageResponse
import com.example.mobilebanking.data.remote.response.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {

    @POST("mobile-bank/v1/auth/sign-up")
    suspend fun signUp(@Body data: SignUpUserDataRequest): Response<TokenResponse>

    @POST("mobile-bank/v1/auth/sign-up/verify")
    suspend fun signUpVerify(@Body data: SignUpVerifyRequest): Response<AccessInRefreshTokenResponse>

    @POST("mobile-bank/v1/auth/sign-in")
    suspend fun signIn(@Body data: SignInUserDataRequest): Response<TokenResponse>

    @POST("mobile-bank/v1/auth/sign-in/verify")
    suspend fun signInVerify(@Body data: SignInVerifyRequest): Response<AccessInRefreshTokenResponse>

    @POST("mobile-bank/v1/auth/update-token")
    suspend fun updateToken(@Body token: UpdateTokenRequest): Response<AccessInRefreshTokenResponse>

    @POST("mobile-bank/v1/auth/sign-out")
    suspend fun signOut(@Header("Authorization") token: String): Response<MessageResponse>

}
