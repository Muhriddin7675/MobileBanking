package com.example.mobilebanking.presentantion.screen.comfirmation

import com.example.mobilebanking.presentantion.screen.pincode.PinCreateScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface ConfirmDirection {
    suspend fun openPinCodeScreen()

    suspend fun pop()
}
class  ConfirmDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): ConfirmDirection {
    override suspend fun openPinCodeScreen() {
        navigator.replaceAll(PinCreateScreen())
    }

    override suspend fun pop() {
        navigator.back()
    }

}