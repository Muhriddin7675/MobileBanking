package com.example.mobilebanking.presentantion.screen.register

import com.example.mobilebanking.presentantion.screen.comfirmation.ConfirmNumberScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface RegisterDirection {
    suspend fun openConfirmationScreen()
}
class RegisterDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): RegisterDirection {
    override suspend fun openConfirmationScreen() {
        navigator.navigateTo(ConfirmNumberScreen())
    }
}