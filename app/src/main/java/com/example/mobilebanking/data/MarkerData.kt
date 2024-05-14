package com.example.mobilebanking.data

data class MarkerData(
    val lat: Double,
    val lng: Double,
    val image: Int,
    val titleBank: String,
    val titleBankInfo: String ="Paynet",
    val textLocation: String,
    val textTelephoneNumber: String = "+998 71 202 07 07",
)