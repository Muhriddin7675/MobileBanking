package com.example.mobilebanking.presentantion.screen.splash

import com.example.mobilebanking.presentantion.screen.pincheck.PinScreen
import com.example.mobilebanking.presentantion.screen.register.RegisterScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface SplashDirection {
suspend fun openRegisterScreen()
suspend fun openPinScreen()
}

class SplashDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): SplashDirection {
    override suspend fun openRegisterScreen() {
        navigator.replace(RegisterScreen())
    }

    override suspend fun openPinScreen() {
    navigator.replace(PinScreen())
    }

}