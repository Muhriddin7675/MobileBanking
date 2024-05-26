package com.example.mobilebanking.presentantion.screen.transfer_card

import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.presentantion.screen.addcard.AddCardScreen
import com.example.mobilebanking.presentantion.screen.transfer_verify.TransferVerifyScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface TransferCardDirections {
    suspend fun backTransferScreen()
    suspend fun navigateTransferVerifyScreen(token: String, data: UserCardData, amount: String)
    suspend fun navigateAddCardScreen()
}

class TransferCardDirectionsImp @Inject constructor(
    private val navigator: AppNavigator
) : TransferCardDirections {
    override suspend fun backTransferScreen() =
        navigator.back()

    override suspend fun navigateTransferVerifyScreen(token: String, data: UserCardData, amount: String) =
        navigator.navigateTo(TransferVerifyScreen(data, amount, token))

    override suspend fun navigateAddCardScreen() =
        navigator.navigateTo(AddCardScreen())

}