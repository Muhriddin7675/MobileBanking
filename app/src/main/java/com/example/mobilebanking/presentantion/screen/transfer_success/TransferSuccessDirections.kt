package com.example.mobilebanking.presentantion.screen.transfer_success

import com.example.mobilebanking.presentantion.screen.pager.MainScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface TransferSuccessDirections {
    suspend fun backMainScreen()
}

class TransferSuccessDirectionsImp @Inject constructor(
    private val navigator: AppNavigator
) : TransferSuccessDirections {
    override suspend fun backMainScreen() =
        navigator.backUntil(MainScreen())

}