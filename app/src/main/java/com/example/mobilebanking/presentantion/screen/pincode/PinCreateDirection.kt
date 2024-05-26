package com.example.mobilebanking.presentantion.screen.pincode

import com.example.mobilebanking.presentantion.screen.pager.MainScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface PinCheckDirection {
suspend fun openMainScreen()
}
class PinCheckDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
):PinCheckDirection{
    override suspend fun openMainScreen() {
        navigator.replace(MainScreen())
    }

}