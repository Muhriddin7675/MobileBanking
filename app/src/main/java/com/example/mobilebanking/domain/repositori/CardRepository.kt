package com.example.mobilebanking.domain.repositori

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.remote.request.CardRequest
import com.example.mobilebanking.data.remote.request.UpdateCardRequest
import com.example.mobilebanking.data.remote.response.GetCardOwnerRequest
import com.example.mobilebanking.data.remote.response.GetCardOwnerResponse
import com.example.mobilebanking.data.remote.response.MessageResponse
import kotlinx.coroutines.flow.Flow


interface CardRepository {
    fun getCards(): Flow<Result<List<CardData>>>
    fun addCard(request: CardRequest): Flow<Result<MessageResponse>>
    fun updateCard(updateCardRequest: UpdateCardRequest):Flow<Result<MessageResponse>>
    fun deleteCard(id:String):Flow<Result<MessageResponse>>
    fun getCardOwner(request: GetCardOwnerRequest) : Flow<Result<GetCardOwnerResponse>>

}