package com.example.mobilebanking.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyClint {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://195.158.16.140/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

