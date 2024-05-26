package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.data.remote.request.TransferRequest
import com.example.mobilebanking.domain.repositori.TransferRepository
import javax.inject.Inject

class TransferUseCase @Inject constructor(
    private val repo: TransferRepository
) {
    operator fun invoke(senderId: String, receiverPan: String, amount: Long) =
        repo.transferMoney(TransferRequest(senderId = senderId, receiverPan = receiverPan, amount = amount))


}