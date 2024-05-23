package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.data.remote.request.CardRequest
import com.example.mobilebanking.domain.CardRepository
import javax.inject.Inject

class AddCardUseCase @Inject constructor(
    private val repo: CardRepository
) {
    operator fun invoke(
        pan: String,
        expiredYear: String,
        expiredMonth: String,
        name: String = "Personal"
    ) =
        repo.addCard(
            CardRequest(
                pan = pan,
                expiredYear = expiredYear,
                expiredMonth = expiredMonth,
                name = name
            )
        )
}