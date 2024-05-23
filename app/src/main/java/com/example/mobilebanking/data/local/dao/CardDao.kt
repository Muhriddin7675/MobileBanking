package com.example.mobilebanking.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mobilebanking.data.local.entity.CardEntity

@Dao
interface CardDao {

    @Query("SELECT * FROM CardEntity")
    fun getAllCards(): List<CardEntity>

    @Insert
    fun addCard(card: CardEntity)

    @Update
    fun updateCard(card: CardEntity)

    @Delete
    fun deleteCard(card: CardEntity)
}
