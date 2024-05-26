package com.example.mobilebanking.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardData(
    val id: Int= 0,
    val name: String = "Default owner",
    val amount: Long = 0,
    val owner: String = "Default owner",
    val pan: String = "0000 0000 0000 0000",
    val expiredYear: String = "",
    val expiredMonth: Int = 0,
    val themeType: Int = 0,
    val isVisible: Boolean = false
) : Parcelable