package com.example.mobilebanking.presentantion.screen.splash

import com.example.mobilebanking.presentantion.screen.login.RegisterScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface SplashDirection {
suspend fun openRegisterScreen()
}

class SplashDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): SplashDirection {
    override suspend fun openRegisterScreen() {
        navigator.replace(RegisterScreen())
    }

}