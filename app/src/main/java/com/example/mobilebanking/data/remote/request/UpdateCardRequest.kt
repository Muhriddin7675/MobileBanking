package com.example.mobilebanking.data.remote.request

import com.google.gson.annotations.SerializedName

class UpdateCardRequest (
    val id:Int,
    val name: String = "Basic",
    @SerializedName("theme-type")
    val themeType:Int,
    @SerializedName("is-visible")
    val isVisible:String
)

