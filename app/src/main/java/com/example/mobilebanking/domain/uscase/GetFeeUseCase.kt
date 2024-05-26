package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.data.remote.request.GetFeeRequest
import com.example.mobilebanking.domain.repositori.TransferRepository
import javax.inject.Inject

class GetFeeUseCase @Inject constructor(
    private val repo: TransferRepository
) {
    operator fun invoke(senderId: String, receiver: String, amount: Long) =
        repo.getFee(GetFeeRequest(senderId, receiver, amount))
}