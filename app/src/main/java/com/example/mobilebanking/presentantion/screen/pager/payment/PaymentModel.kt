package com.example.mobilebanking.presentantion.screen.pager.payment

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PaymentModel @Inject constructor(
    private val direction: PaymentDirection
) : ViewModel(), PaymentContract.Model {

    override val container = container<PaymentContract.UIState, PaymentContract.SideEffect>(
        PaymentContract.UIState.InitState
    )

    override fun onEventDispatcher(intent: PaymentContract.Intent) {

    }
}


