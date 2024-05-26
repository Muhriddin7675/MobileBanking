package com.example.mobilebanking.presentantion.screen.transfer_verify

import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.presentantion.screen.transfer_success.TransferSuccessScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface TransferVerifyDirections {
    suspend fun navigateTransferSuccessScreen(data: UserCardData, amount: String)
    suspend fun backToTransferCardScreen()
}
class TransferVerifyDirectionsImp @Inject constructor(
    private val navigator: AppNavigator
) : TransferVerifyDirections {

    override suspend fun navigateTransferSuccessScreen(data: UserCardData, amount: String) {
        navigator.navigateTo(TransferSuccessScreen(data, amount.toLong()))
    }

    override suspend fun backToTransferCardScreen() =
        navigator.back()

}