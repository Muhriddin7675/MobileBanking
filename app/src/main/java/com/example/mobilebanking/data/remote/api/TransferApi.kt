package com.example.mobilebanking.data.remote.api

import com.example.mobilebanking.data.remote.response.GetFeeResponse
import com.example.mobilebanking.data.remote.response.GetHistoryResponse
import com.example.mobilebanking.data.remote.response.TransferResendResponse
import com.example.mobilebanking.data.remote.response.TransferResponse
import com.example.mobilebanking.data.remote.response.TransferVerifyResponse
import com.example.mobilebanking.data.remote.request.GetFeeRequest
import com.example.mobilebanking.data.remote.request.TransferRequest
import com.example.mobilebanking.data.remote.request.TransferResendRequest
import com.example.mobilebanking.data.remote.request.TransferVerifyRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TransferApi {
    @POST("mobile-bank/v1/transfer/fee")
    suspend fun getFee(@Body request: GetFeeRequest) : Response<GetFeeResponse>

    @POST("mobile-bank/v1/transfer/transfer")
    suspend fun transferMoney(@Body request: TransferRequest): Response<TransferResponse>

    @POST("mobile-bank/v1/transfer/transfer/verify")
    suspend fun transferVerify(@Body request: TransferVerifyRequest) : Response<TransferVerifyResponse>

    @POST("mobile-bank/v1/transfer/transfer/resend")
    suspend fun transferResend(@Body request: TransferResendRequest) : Response<TransferResendResponse>

    @GET("mobile-bank/v1/transfer/history")
    suspend fun getHistory(@Query("size") count: Int, @Query("current-page") page: Int) : Response<GetHistoryResponse>

}