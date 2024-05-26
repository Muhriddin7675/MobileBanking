package com.example.mobilebanking.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetFeeResponse(
    @SerializedName("fee")
    val fee: String,
    @SerializedName("amount")
    val amount: String
)

/*
* {
    "fee": 1,
    "amount": 101000
}
* */
