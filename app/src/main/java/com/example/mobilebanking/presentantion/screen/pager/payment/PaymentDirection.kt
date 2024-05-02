package com.example.mobilebanking.presentantion.screen.pager.payment

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface PaymentDirection {

}

class PaymentDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : PaymentDirection {


}

