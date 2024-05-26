package com.example.mobilebanking.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetCardOwnerResponse(
    @SerializedName("pan")
    val user: String
)
