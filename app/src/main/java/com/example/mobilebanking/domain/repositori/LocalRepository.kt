package com.example.mobilebanking.domain.repositori

import com.example.mobilebanking.data.local.entity.CardEntity
import com.example.mobilebanking.data.local.entity.LastTransferUserEntity
import com.example.mobilebanking.data.model.CardData

interface LocalRepository {
    //room
    fun getAllCardsLocal() : List<CardData>
    fun addCardLocal(card: CardEntity)
    fun deleteCardLocal(card: CardEntity)
    fun updateCardLocal(card: CardEntity)
    fun getAllUsers(): List<LastTransferUserEntity>
    suspend fun deleteAllItems()
    suspend fun addUser(data: LastTransferUserEntity)
    suspend fun updateUser(data: LastTransferUserEntity)
    suspend fun deleteUser(data: LastTransferUserEntity)
    fun search(pan: String): List<LastTransferUserEntity>

}