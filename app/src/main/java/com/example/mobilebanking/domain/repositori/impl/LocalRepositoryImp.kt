package com.example.mobilebanking.domain.repositori.impl

import com.example.mobilebanking.data.Mapper.toUIData
import com.example.mobilebanking.data.local.dao.CardDao
import com.example.mobilebanking.data.local.dao.LastTransferUserDao
import com.example.mobilebanking.data.local.entity.CardEntity
import com.example.mobilebanking.data.local.entity.LastTransferUserEntity
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.domain.repositori.LocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepositoryImp @Inject constructor(
    private val pref: MyShared,
    private val cardDao: CardDao,
    private val usersDao: LastTransferUserDao
) : LocalRepository {

    //room
    init {
        CoroutineScope(Dispatchers.IO).launch {

        }
    }
    override fun getAllCardsLocal(): List<CardData> =
        cardDao.getAllCards().map { it.toUIData() }

    override fun addCardLocal(card: CardEntity) =
        cardDao.addCard(card)

    override fun deleteCardLocal(card: CardEntity) =
        cardDao.deleteCard(card)

    override fun updateCardLocal(card: CardEntity) =
        cardDao.updateCard(card)

    override fun getAllUsers(): List<LastTransferUserEntity> = usersDao.getAllUsers()
    override suspend fun deleteAllItems() {
        withContext(Dispatchers.IO) {
            usersDao.deleteAllItems()
        }
    }

    override suspend fun addUser(data: LastTransferUserEntity) {
        withContext(Dispatchers.IO) {
            usersDao.addUser(data)
        }
    }

    override suspend fun updateUser(data: LastTransferUserEntity) {
        withContext(Dispatchers.IO) {
            usersDao.updateUser(data)
        }
    }

    override suspend fun deleteUser(data: LastTransferUserEntity) {
        withContext(Dispatchers.IO) {
            usersDao.deleteUser(data)
        }
    }

    override fun search(pan: String) = usersDao.search(pan)


}