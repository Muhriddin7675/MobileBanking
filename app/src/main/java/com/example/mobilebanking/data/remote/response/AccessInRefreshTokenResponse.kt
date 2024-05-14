package com.example.mobilebanking.data.remote.response

import com.google.gson.annotations.SerializedName

class AccessInRefreshTokenResponse(
    @SerializedName("refresh-token")
    val refreshToken :String,
    @SerializedName("access-token")
    val accessToken :String,
)