package com.example.mobilebanking.data.remote.response

import com.example.mobilebanking.data.model.CardData
import com.google.gson.annotations.SerializedName

class CardResponse(
    val id: Int,
    val name: String,
    val amount: Long,
    val owner: String,
    val pan: String,
    @SerializedName("expired-year")
    val expiredYear: String,
    @SerializedName("expired-month")
    val expiredMonth: String,
    @SerializedName("theme-type")
    val themeType: String,
    @SerializedName("is-visible")
    val isVisible: String
)

fun CardResponse.toCardData(): CardData {
    return CardData(
        id = id,
        name = name,
        amount = amount,
        owner = owner,
        pan = pan,
        expiredYear = expiredYear,
        expiredMonth = expiredMonth.toInt(),
        isVisible = isVisible.toBoolean()
    )
}

/*"id": 21,
"name": "Personal",
"amount": 20000000,
"owner": "Muhammadali",
"pan": "5420",
"expired-year": 2023,
"expired-month": 9,
"theme-type": 4,
"is-visible": true*/
