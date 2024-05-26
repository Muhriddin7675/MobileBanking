package com.example.mobilebanking.data.remote.request

import com.google.gson.annotations.SerializedName

data class TransferResendRequest(
    @SerializedName("token")
    val token: String
)

/*
{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOi8vMTQzLjE5OC40OC4yMjI6ODQvdjEvbW9iaWxlLWJhbmsvYXV0aCIsImFjY291bnQtaWQiOiIyIiwiaXNzIjoiaHR0cDovLzE0My4xOTguNDguMjIyOjg0L3YxL21vYmlsZS1iYW5rIiwiZXhwIjoxNjcxMTY4MTg3fQ.2H86ElDpvpbzOO6luE9okfThIyyHXkrwsU3CrliHvVw"
}
* */
