package com.example.mobilebanking.data.currency

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class Service {
    private val file = File.createTempFile("currency", "txt")
    private val gson = Gson()

    fun getCurrencyList(): ArrayList<Currency> {
        val jsonString = file.readText()

        val list: ArrayList<Currency> =
            if (jsonString.isEmpty()) {
                ArrayList()
            } else {
                val type = object : TypeToken<List<Currency>>() {}.type
                gson.fromJson(jsonString, type)
            }

        return list
    }

    fun writeText(jsonString: String) {
        file.writeBytes(jsonString.toByteArray())
    }
}