package com.example.mobilebanking.presentantion.screen.paymentcard

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface PaymentCardDirection {
    suspend fun PopBackStack()
}

@Singleton
class PaymentCardDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : PaymentCardDirection {
    override suspend fun PopBackStack() {
        navigator.back()
    }

}