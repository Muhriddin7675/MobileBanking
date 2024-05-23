package com.example.mobilebanking.presentantion.screen.card_details

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.presentantion.screen.card_theme.CardThemeScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface CardDetailsDirections {
    suspend fun backToHomeScreen()
    suspend fun navigateCardTheme(data: CardData)
}

class CardDetailsDirectionsImp @Inject constructor(
    private val navigator: AppNavigator
) : CardDetailsDirections {
    override suspend fun backToHomeScreen() =
        navigator.back()

    override suspend fun navigateCardTheme(data: CardData) =
        navigator.replace(CardThemeScreen(data))
}