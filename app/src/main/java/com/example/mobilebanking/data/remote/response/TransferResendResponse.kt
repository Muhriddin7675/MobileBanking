package com.example.mobilebanking.data.remote.response

import com.google.gson.annotations.SerializedName

data class TransferResendResponse(
    @SerializedName("token")
    val token: String
)

/*
{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOi8vMTQzLjE5OC40OC4yMjI6ODQvdjEvbW9iaWxlLWJhbmsvYXV0aCIsImNvZGUiOiI2ODA1MjUiLCJwaG9uZSI6Ijk5Mzk0NjI4MCIsImlzcyI6Imh0dHA6Ly8xNDMuMTk4LjQ4LjIyMjo4NC92MS9tb2JpbGUtYmFuayIsImJvZHkiOiIwIzEyMzQ1Njc4OTg3NjU0MjAjMTIzNDU2Nzg5ODc2NTQ0MCMxMDAxMDAjIyIsImV4cCI6MTY3MTMzNDQ4MX0.ZHPwq4rq2xqGwI4gtRRvzNjwasS0qDIIiHedh5K7aNo"
}
* */