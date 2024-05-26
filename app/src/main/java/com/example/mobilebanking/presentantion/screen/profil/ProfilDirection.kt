package com.example.mobilebanking.presentantion.screen.profil

import com.example.mobilebanking.presentantion.screen.identity.IdentityVerificationScreen
import com.example.mobilebanking.presentantion.screen.map.MapScreen
import com.example.mobilebanking.presentantion.screen.register.RegisterScreen
import com.example.mobilebanking.presentantion.screen.setting.AppSettingScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface ProfilDirection {
suspend fun openMapScreen()
suspend fun popBackStack()
suspend fun logOut()
suspend fun openSettingScreen()

suspend fun openIdentityVerificationScreen()
}
class ProfilDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
):ProfilDirection{
    override suspend fun openMapScreen() {
        navigator.navigateTo(MapScreen())
    }

    override suspend fun popBackStack() {
        navigator.back()
    }
    override suspend fun logOut() {
        navigator.replaceAll(RegisterScreen())
    }

    override suspend fun openSettingScreen() {
        navigator.navigateTo(AppSettingScreen())
    }

    override suspend fun openIdentityVerificationScreen() {
        navigator.navigateTo(IdentityVerificationScreen())
    }

}