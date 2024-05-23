package com.example.mobilebanking.data.remote.request

import com.google.gson.annotations.SerializedName

class CardRequest(
    val pan: String,
    @SerializedName("expired-year")
    val expiredYear: String,
    @SerializedName("expired-month")
    val expiredMonth: String,
    val name:String = "Personal"
)
/*

"pan": "1234567898765432",
"expired-year": "2023",
"expired-month": "1",
"name": "Personal"*/
