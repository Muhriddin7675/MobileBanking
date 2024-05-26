package com.example.mobilebanking.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetHistoryResponse(
    @SerializedName("child")
    val child: List<Child>,
    @SerializedName("current-page")
    val currentPage: Int,
    @SerializedName("total-elements")
    val totalElements: Int,
    @SerializedName("total-pages")
    val totalPages: Int
)