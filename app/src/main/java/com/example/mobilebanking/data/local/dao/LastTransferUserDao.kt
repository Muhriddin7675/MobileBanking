package com.example.mobilebanking.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mobilebanking.data.local.entity.LastTransferUserEntity

@Dao
interface LastTransferUserDao {
    @Query("SELECT * FROM LastTransferUserEntity")
    fun getAllUsers(): List<LastTransferUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(data: LastTransferUserEntity)

    @Update
    fun updateUser(data: LastTransferUserEntity)

    @Delete
    fun deleteUser(data: LastTransferUserEntity)

    @Query("SELECT * FROM LastTransferUserEntity WHERE pan LIKE :pan || '%'")
    fun search(pan: String): List<LastTransferUserEntity>

    @Query("DELETE FROM LastTransferUserEntity")
    fun deleteAllItems()
}