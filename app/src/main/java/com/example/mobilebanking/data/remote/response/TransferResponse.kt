package com.example.mobilebanking.data.remote.response

import com.google.gson.annotations.SerializedName

data class TransferResponse(
    @SerializedName("token")
    val token: String
)

/*
{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOi8vMTQzLjE5OC40OC4yMjI6ODQvdjEvbW9iaWxlLWJhbmsvYXV0aCIsImNvZGUiOiIxNDIzOTIiLCJwaG9uZSI6Iis5OTg5OTM5NDYyODAiLCJpc3MiOiJodHRwOi8vMTQzLjE5OC40OC4yMjI6ODQvdjEvbW9iaWxlLWJhbmsiLCJib2R5IjoiMTIzNDU2Nzg5ODc2NTQzMiMxMjM0NTY3ODk4NzY1NDMzIzEwMDEwMCMiLCJleHAiOjE2NzExNjgwMTZ9.m9YQZ_UH8aLQkTdQw8kXjGzRLD8OrYP8yxpHXUQ4b9w"
}
* */