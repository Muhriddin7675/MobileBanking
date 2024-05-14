package com.example.mobilebanking.data.remote.request

import com.google.gson.annotations.SerializedName

class UpdateTokenRequest(
    @SerializedName("refresh-token")
    val refreshToken: String
)