package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.data.remote.request.TransferResendRequest
import com.example.mobilebanking.domain.repositori.TransferRepository
import javax.inject.Inject

class TransferResendUseCase @Inject constructor(
    private val repo: TransferRepository
) {
    operator fun invoke(token: String) =
        repo.transferResend(TransferResendRequest(token))
}