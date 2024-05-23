package com.example.mobilebanking.presentantion.screen.card_theme

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.presentantion.screen.card_details.CardDetailsScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface CardThemeDirections {
    suspend fun backCardDetails(data: CardData)
}
@Singleton
class CardThemeDirectionsImp @Inject constructor(
    private val navigator: AppNavigator
) : CardThemeDirections {
    override suspend fun backCardDetails(data: CardData) =
        navigator.replace(CardDetailsScreen(data))

}