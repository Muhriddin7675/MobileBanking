package com.example.mobilebanking.data.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateTokenResponse(
    @SerializedName("refresh-token")
    val refreshToken: String,
    @SerializedName("access-token")
    val accessToken: String,
)
