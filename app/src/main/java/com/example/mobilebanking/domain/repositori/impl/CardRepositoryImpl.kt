package com.example.mobilebanking.domain.repositori.impl

import com.example.mobilebanking.data.Mapper.toCardData
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.remote.api.AddCardApi
import com.example.mobilebanking.data.remote.request.CardRequest
import com.example.mobilebanking.data.remote.request.UpdateCardRequest
import com.example.mobilebanking.data.remote.response.GetCardOwnerRequest
import com.example.mobilebanking.data.remote.response.GetCardOwnerResponse
import com.example.mobilebanking.data.remote.response.MessageResponse
import com.example.mobilebanking.domain.repositori.CardRepository
import com.example.mobilebanking.util.emitWith
import com.example.mobilebanking.util.safetyFlow
import com.example.mobilebanking.util.toResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepositoryImpl @Inject constructor(
    private val cardApi: AddCardApi,
    private val myShared: MyShared
) : CardRepository {

    override fun getCards(): Flow<Result<List<CardData>>> = safetyFlow {
        cardApi.getCards().toResult().map {
            it.map {
                it.toCardData()
            }
        }.emitWith()
    }

    override fun addCard(request: CardRequest): Flow<Result<MessageResponse>> = safetyFlow {
        cardApi.addCard(request)
            .toResult()
            .onSuccess { getCards() }
            .emitWith()
    }


    override fun updateCard(updateCardRequest: UpdateCardRequest) = safetyFlow {
        cardApi.updateCard(updateCardRequest)
            .toResult()
            .onSuccess { getCards() }
            .emitWith()

    }

    override fun deleteCard(id: String): Flow<Result<MessageResponse>> = safetyFlow {
        cardApi.deleteCard(id).toResult()
            .onSuccess { }
            .emitWith()
    }

    override fun getCardOwner(request: GetCardOwnerRequest): Flow<Result<GetCardOwnerResponse>>  = safetyFlow{
        cardApi.getCardOwner(request)
            .toResult()
            .emitWith()
    }
}