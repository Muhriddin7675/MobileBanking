package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.data.remote.request.UpdateCardRequest
import com.example.mobilebanking.domain.repositori.CardRepository
import javax.inject.Inject

class UpdateCardUseCase @Inject constructor(
    private val repo: CardRepository
) {
    operator fun invoke(id: Int, name: String, themeType: Int, isVisible: String) =
        repo.updateCard(
            UpdateCardRequest(
                id = id,
                name = name,
                themeType = themeType,
                isVisible = isVisible
            )
        )
}