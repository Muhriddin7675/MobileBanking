package com.example.mobilebanking.presentantion.screen.addcard

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface AddCardDirection {
suspend fun popBackStack()
}
class AddCardDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
):AddCardDirection{
    override suspend fun popBackStack() {
        navigator.back()
    }

}