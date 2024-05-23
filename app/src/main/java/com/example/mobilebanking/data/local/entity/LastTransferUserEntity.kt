package com.example.mobilebanking.data.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["pan", "owner"])
data class LastTransferUserEntity(
    val pan: String,
    val owner: String
)
