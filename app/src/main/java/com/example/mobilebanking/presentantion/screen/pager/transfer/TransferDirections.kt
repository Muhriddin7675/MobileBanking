package com.example.mobilebanking.presentantion.screen.pager.transfer

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.presentantion.screen.addcard.AddCardScreen
import com.example.mobilebanking.presentantion.screen.to_mycard.TransferMyCardScreen
import com.example.mobilebanking.presentantion.screen.transfer_card.TransferCardScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface TransferDirections {
    suspend fun navigateTransferCard(
        userCardData: UserCardData
    )

    suspend fun navigateAddCardScreen()

    suspend fun navigateTransferMyCardScreen(list: List<CardData>, data: UserCardData)
}


class TransferDirectionsImp @Inject constructor(
    private val navigator: AppNavigator
) : TransferDirections {
    override suspend fun navigateTransferCard(userCardData: UserCardData) {
        navigator.navigateTo(TransferCardScreen(userCardData))
    }

    override suspend fun navigateAddCardScreen() =
        navigator.navigateTo(AddCardScreen())

    override suspend fun navigateTransferMyCardScreen(list: List<CardData>, data: UserCardData) {
        navigator.navigateTo(TransferMyCardScreen(list, data))
    }
}
