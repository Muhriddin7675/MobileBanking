package com.example.mobilebanking.presentantion.screen.to_mycard

import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.presentantion.screen.transfer_verify.TransferVerifyScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface TransferMyCardDirections {
    suspend fun backTransferScreen()
    suspend fun navigateTransferVerifyScreen(token: String, data: UserCardData, amount: String)
}

class TransferMyCardDirectionsImp @Inject constructor(
    private val navigator: AppNavigator
) : TransferMyCardDirections {
    override suspend fun backTransferScreen() =
        navigator.back()

    override suspend fun navigateTransferVerifyScreen(
        token: String,
        data: UserCardData,
        amount: String
    ) = navigator.navigateTo(TransferVerifyScreen(data, amount, token))

}