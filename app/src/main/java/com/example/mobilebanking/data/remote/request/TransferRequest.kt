package com.example.mobilebanking.data.remote.request

import com.google.gson.annotations.SerializedName

data class TransferRequest(
    @SerializedName("type")
    val type: String = "third-card",
    @SerializedName("sender-id")
    val senderId: String,
    @SerializedName("receiver-pan")
    val receiverPan: String,
    @SerializedName("amount")
    val amount: Long
)

/*
* {
    "type": "third-card",
    "sender-id": "7",
    "receiver-pan": "1234567898765432",
    "amount": 100100
}
* */
