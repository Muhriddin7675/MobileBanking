package com.example.mobilebanking.data.remote.request

import com.google.gson.annotations.SerializedName

data class GetFeeRequest(
    @SerializedName("sender-id")
    val senderId: String,
    @SerializedName("receiver")
    val receiver: String,
    @SerializedName("amount")
    val amount: Long
)

/*
* {
    "sender-id": "7",
    "receiver": "0008000800080026",
    "amount": 100000
}
* */
