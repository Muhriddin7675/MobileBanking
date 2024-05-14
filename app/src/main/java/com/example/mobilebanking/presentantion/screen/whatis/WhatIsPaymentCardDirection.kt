package com.example.mobilebanking.presentantion.screen.whatis

import com.example.mobilebanking.presentantion.screen.coditions.ConditionsScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface WhatIsPaymentCardDirection {
    suspend fun popBackStack()
    suspend fun openConditionsScreen()
}

@Singleton
class WhatIsPaymentCardDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : WhatIsPaymentCardDirection {
    override suspend fun popBackStack() {
        navigator.back()
    }

    override suspend fun openConditionsScreen() {
        navigator.navigateTo(ConditionsScreen())
    }

}