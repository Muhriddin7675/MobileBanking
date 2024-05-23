package com.example.mobilebanking.presentantion.screen.allcard

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.presentantion.screen.addcard.AddCardScreen
import com.example.mobilebanking.presentantion.screen.card_details.CardDetailsScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface AllCardDirection {
    suspend fun popBackStack()
    suspend fun openCardDetail(data: CardData)
    suspend fun openAddCardScreen()
}

@Singleton
class AllCardDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : AllCardDirection {
    override suspend fun popBackStack() {
        navigator.back()
    }

    override suspend fun openCardDetail(data: CardData) {
        navigator.navigateTo(CardDetailsScreen(data))
    }

    override suspend fun openAddCardScreen() {
        navigator.navigateTo(AddCardScreen())
    }

}