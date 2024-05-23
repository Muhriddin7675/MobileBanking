package com.example.mobilebanking.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CardEntity(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("amount")
    val amount: Long = 0,
    @SerializedName("owner")
    val owner: String = "",
    @SerializedName("pan")
    val pan: String,
    @SerializedName("expired-year")
    val expiredYear: Int,
    @SerializedName("expired-month")
    val expiredMonth: Int,
    @SerializedName("theme-type")
    val themeType: Int = 0,
    @SerializedName("is-visible")
    val isVisible: Boolean = true,
)
