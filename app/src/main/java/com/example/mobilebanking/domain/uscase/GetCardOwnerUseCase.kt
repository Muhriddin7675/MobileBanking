package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.data.remote.response.GetCardOwnerRequest
import com.example.mobilebanking.domain.repositori.CardRepository
import javax.inject.Inject

class GetCardOwnerUseCase @Inject constructor(
    private val repo: CardRepository
) {
    operator fun invoke(pan: String) =
        repo.getCardOwner(GetCardOwnerRequest(pan))
}