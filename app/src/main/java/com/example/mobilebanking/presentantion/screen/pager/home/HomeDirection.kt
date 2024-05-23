package com.example.mobilebanking.presentantion.screen.pager.home

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.presentantion.screen.addcard.AddCardScreen
import com.example.mobilebanking.presentantion.screen.allcard.AllCardScreen
import com.example.mobilebanking.presentantion.screen.card_details.CardDetailsScreen
import com.example.mobilebanking.presentantion.screen.identity.IdentityVerificationScreen
import com.example.mobilebanking.presentantion.screen.paymentcard.PaymentCardScreen
import com.example.mobilebanking.presentantion.screen.profil.ProfilScreen
import com.example.mobilebanking.presentantion.screen.whatis.WhatIsPaymentCardScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface HomeDirection {
    suspend fun openProfilScreen()
    suspend fun openAddCardScreen()
    suspend fun openPaymentCardScreen()
    suspend fun openWhatIsPaymentCard()
    suspend fun openIdentityVerificationScreen()
    suspend fun openAllCardScreen()

    suspend fun openCardDetailScreen(cardData: CardData)
}

class HomeDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : HomeDirection {
    override suspend fun openProfilScreen() {
        navigator.navigateTo(ProfilScreen())
    }

    override suspend fun openAddCardScreen() {
        navigator.navigateTo(AddCardScreen())
    }

    override suspend fun openPaymentCardScreen() {
        navigator.navigateTo(PaymentCardScreen())
    }

    override suspend fun openWhatIsPaymentCard() {
        navigator.navigateTo(WhatIsPaymentCardScreen())
    }

    override suspend fun openIdentityVerificationScreen() {
        navigator.navigateTo(IdentityVerificationScreen())
    }

    override suspend fun openAllCardScreen() {
        navigator.navigateTo(AllCardScreen())
    }

    override suspend fun openCardDetailScreen(cardData: CardData) {
        navigator.navigateTo(CardDetailsScreen(cardData))
    }


}