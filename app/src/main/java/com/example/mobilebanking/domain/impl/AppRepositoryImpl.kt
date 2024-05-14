package com.example.mobilebanking.domain.impl

import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.data.remote.api.UserApi
import com.example.mobilebanking.data.remote.request.SignInUserDataRequest
import com.example.mobilebanking.data.remote.request.SignInVerifyRequest
import com.example.mobilebanking.data.remote.request.SignUpUserDataRequest
import com.example.mobilebanking.data.remote.request.SignUpVerifyRequest
import com.example.mobilebanking.data.remote.request.UpdateTokenRequest
import com.example.mobilebanking.data.remote.response.MessageResponse
import com.example.mobilebanking.domain.AppRepository
import com.example.mobilebanking.util.myLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val api: UserApi,
    private val myShared: MyShared
) : AppRepository {

    override fun signUp(data: SignUpUserDataRequest): Flow<Result<Unit>> = flow {
        val response = api.signUp(data)
        myLog(" sign up phone: ${data.phone}  password: ${data.password}")
        if (response.isSuccessful && response.body() != null) {
            val tokenData = response.body()!!
            myShared.setToken(tokenData.token)
            emit(Result.success(Unit))
        } else {
            myLog("sign up error ${response.code()}")
            emit(Result.failure(Exception("Failure signUp")))
        }

    }.catch { emit(Result.failure(Exception("Unknown exception"))) }
        .flowOn(Dispatchers.IO)


    override fun signUpVerify(code: String): Flow<Result<Unit>> = flow {
        val response = api.signUpVerify(SignUpVerifyRequest(myShared.getToken(), code))
        if (response.isSuccessful && response.body() != null) {
            val tokenData = response.body()!!

            myShared.setAccessToken(tokenData.accessToken)
            myShared.setRefreshToken(tokenData.refreshToken)
            emit(Result.success(Unit))
        } else emit(Result.failure(Exception("Failure signUpVerify")))
    }.catch { emit(Result.failure(Exception("Unknown exception"))) }
        .flowOn(Dispatchers.IO)


    override  fun signIn(phone: String, password: String): Flow<Result<Unit>> = flow {
        val response = api.signIn(SignInUserDataRequest(phone, password))
        myLog(" sign In phone: ${phone}  password: ${password}")
        if (response.isSuccessful && response.body() != null) {
            val tokenData = response.body()!!
            myShared.setToken(tokenData.token)
            emit(Result.success(Unit))
        } else {
            myLog("sign in error ${response.code()}")
            emit(Result.failure(Exception("Fail sin In exception")))
        }
    }.catch { emit(Result.failure(Exception("Unknown exception"))) }
        .flowOn(Dispatchers.IO)


    override fun signInVerify(code: String): Flow<Result<Unit>> = flow {
        val response = api.signInVerify(SignInVerifyRequest(myShared.getToken(), code))
        if (response.isSuccessful && response.body() != null) {
            val tokenData = response.body()!!
            myShared.setRefreshToken(tokenData.refreshToken)
            myShared.setAccessToken(tokenData.accessToken)
            emit(Result.success(Unit))
        } else emit(Result.failure(Exception("Failure signInVerify")))
    }.catch { emit(Result.failure(Exception("Unknown exception"))) }
        .flowOn(Dispatchers.IO)


    override fun updateToken(): Flow<Result<Unit>> = flow {
        val response = api.updateToken(UpdateTokenRequest(myShared.getRefreshToken()))
        if (response.isSuccessful && response.body() != null) {
            val tokenData = response.body()!!
            myShared.setRefreshToken(tokenData.refreshToken)
            myShared.setAccessToken(tokenData.accessToken)
            emit(Result.success(Unit))
        } else emit(Result.failure(Exception("Failure updateToken")))
    }.catch { emit(Result.failure(Exception("Unknown exception"))) }
        .flowOn(Dispatchers.IO)

    override fun signOut(): Flow<Result<MessageResponse>> = flow {
        val response = api.signOut(token = myShared.getToken())
        if (response.isSuccessful && response.body() != null) {
            val tokenData = response.body()!!
            emit(Result.success(MessageResponse(tokenData.message)))
        } else {
            myLog("sign out error ${response.code()}")
            emit(Result.failure(Exception("Failure sign Out")))
        }
    }.catch { emit(Result.failure(Exception("Unknown exception"))) }
        .flowOn(Dispatchers.IO)

}

