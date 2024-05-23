package com.example.mobilebanking.data.model

import java.io.Serializable

data class UserCardData(
    val id: Int,
    val pan: String,
    val owner: String
) : Serializable