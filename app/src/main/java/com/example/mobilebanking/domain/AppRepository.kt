package com.example.mobilebanking.domain

import com.example.mobilebanking.data.remote.request.SignUpUserDataRequest
import com.example.mobilebanking.data.remote.response.MessageResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun signUp(data: SignUpUserDataRequest): Flow<Result<Unit>>

    fun signUpVerify(code: String): Flow<Result<Unit>>

    fun signIn(phone: String, password: String): Flow<Result<Unit>>

    fun signInVerify(code: String): Flow<Result<Unit>>

    fun updateToken(): Flow<Result<Unit>>

    fun signOut(): Flow<Result<MessageResponse>>
}