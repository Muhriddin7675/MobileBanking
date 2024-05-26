package com.example.mobilebanking.domain.repositori.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mobilebanking.data.remote.TestPaginationSource
import com.example.mobilebanking.data.remote.api.TransferApi
import com.example.mobilebanking.data.remote.request.GetFeeRequest
import com.example.mobilebanking.data.remote.request.TransferRequest
import com.example.mobilebanking.data.remote.request.TransferResendRequest
import com.example.mobilebanking.data.remote.request.TransferVerifyRequest
import com.example.mobilebanking.data.remote.response.Child
import com.example.mobilebanking.data.remote.response.GetFeeResponse
import com.example.mobilebanking.data.remote.response.TransferResendResponse
import com.example.mobilebanking.data.remote.response.TransferResponse
import com.example.mobilebanking.data.remote.response.TransferVerifyResponse
import com.example.mobilebanking.domain.repositori.TransferRepository
import com.example.mobilebanking.util.emitWith
import com.example.mobilebanking.util.safetyFlow
import com.example.mobilebanking.util.toResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val api: TransferApi
) : TransferRepository {
    override fun getFee(request: GetFeeRequest): Flow<Result<GetFeeResponse>> = safetyFlow {
        api.getFee(request)
            .toResult()
            .emitWith()
    }

    override fun transferMoney(request: TransferRequest): Flow<Result<TransferResponse>> = safetyFlow {
        api.transferMoney(request)
            .toResult()
            .emitWith()
    }

    override fun transferVerify(request: TransferVerifyRequest): Flow<Result<TransferVerifyResponse>> = safetyFlow {
        api.transferVerify(request)
            .toResult()
            .emitWith()
    }

    override fun transferResend(request: TransferResendRequest): Flow<Result<TransferResendResponse>> = safetyFlow {
        api.transferResend(request)
            .toResult()
            .emitWith()
    }

    override fun getHistory(): Flow<PagingData<Child>> = Pager(
            config = PagingConfig(10),
            pagingSourceFactory = { TestPaginationSource(api) }
        ).flow

}