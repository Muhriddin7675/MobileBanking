package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.domain.CardRepository
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(
    private val repo: CardRepository
) {
    operator fun invoke(id: String) =
        repo.deleteCard(id)
}