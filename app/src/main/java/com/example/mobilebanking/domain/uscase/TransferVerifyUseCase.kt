package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.data.remote.request.TransferVerifyRequest
import com.example.mobilebanking.domain.repositori.TransferRepository
import javax.inject.Inject

class TransferVerifyUseCase @Inject constructor(
    private val repo: TransferRepository
) {
    operator fun invoke(token: String, code: String) =
        repo.transferVerify(TransferVerifyRequest(token, code))
}