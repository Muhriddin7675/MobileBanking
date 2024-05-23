package com.example.mobilebanking.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilebanking.data.local.dao.CardDao
import com.example.mobilebanking.data.local.dao.LastTransferUserDao
import com.example.mobilebanking.data.local.entity.CardEntity
import com.example.mobilebanking.data.local.entity.LastTransferUserEntity

@Database(entities = [CardEntity::class, LastTransferUserEntity::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun cardDao() : CardDao

    abstract fun usersDao() : LastTransferUserDao
}