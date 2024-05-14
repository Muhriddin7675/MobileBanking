package com.example.mobilebanking.data.local.pref

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyShared @Inject constructor(
    private val pref: SharedPreferences
) {
    //
    fun setToken(token: String) {
        pref.edit().putString("TOKEN", token).apply()
    }

    fun getToken(): String =
        pref.getString("TOKEN", "token").toString()

    //
    fun setRefreshToken(refreshToken: String) {
        pref.edit().putString("REFRESH_TOKEN", refreshToken).apply()
    }

    fun getRefreshToken(): String =
        pref.getString("REFRESH_TOKEN", "token").toString()

    //
    fun setAccessToken(accessToken: String) {
        pref.edit().putString("ACCESS_TOKEN", accessToken).apply()
    }

    fun getAccessToken(): String =
        pref.getString("ACCESS_TOKEN", "token").toString()

    fun setPassword(password: String) {
        pref.edit().putString("PASSWORD", password).apply()
    }

    fun getPassword(): String =
        pref.getString("PASSWORD", "password").toString()

    fun setPinCode(pinCode: String) {
        pref.edit().putString("PIN_CODE", pinCode).apply()
    }
    fun getPinCode(): String =
        pref.getString("PIN_CODE", "").toString()

    fun setSingInSignUpState(bool: Boolean) {
        pref.edit().putBoolean("SIGN_IN_UP", bool).apply()
    }

    fun getSignInSignUpState(): Boolean = pref.getBoolean("SIGN_IN_UP", true)

    fun setLanguage(language: Boolean) {
        pref.edit().putBoolean("LANGUAGE", language).apply()
    }

    fun getLanguage(): Boolean =
        pref.getBoolean("LANGUAGE", true)

}