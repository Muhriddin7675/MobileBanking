package com.example.mobilebanking.domain.uscase

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.domain.repositori.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class  GetAllCardUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {
    operator fun invoke(): Flow<Result<List<CardData>>> =
        cardRepository.getCards()

}