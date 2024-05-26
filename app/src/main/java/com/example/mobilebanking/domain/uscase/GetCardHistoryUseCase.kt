package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.domain.repositori.TransferRepository
import javax.inject.Inject

class GetCardHistoryUseCase @Inject constructor(
    private val repo: TransferRepository
) {
    operator fun invoke() = repo.getHistory()
}