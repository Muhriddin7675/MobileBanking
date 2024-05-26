package com.example.mobilebanking.domain.repositori

import androidx.paging.PagingData
import com.example.mobilebanking.data.remote.response.GetFeeResponse
import com.example.mobilebanking.data.remote.response.TransferResendResponse
import com.example.mobilebanking.data.remote.response.TransferResponse
import com.example.mobilebanking.data.remote.response.TransferVerifyResponse
import com.example.mobilebanking.data.remote.request.GetFeeRequest
import com.example.mobilebanking.data.remote.request.TransferRequest
import com.example.mobilebanking.data.remote.request.TransferResendRequest
import com.example.mobilebanking.data.remote.request.TransferVerifyRequest
import com.example.mobilebanking.data.remote.response.Child
import kotlinx.coroutines.flow.Flow

interface TransferRepository {
    fun getFee(request: GetFeeRequest) : Flow<Result<GetFeeResponse>>

    fun transferMoney(request: TransferRequest) : Flow<Result<TransferResponse>>

    fun transferVerify(request: TransferVerifyRequest) : Flow<Result<TransferVerifyResponse>>

    fun transferResend(request: TransferResendRequest) : Flow<Result<TransferResendResponse>>

    fun getHistory() : Flow<PagingData<Child>>
}