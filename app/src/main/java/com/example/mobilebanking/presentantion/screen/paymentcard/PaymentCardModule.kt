package com.example.mobilebanking.presentantion.screen.paymentcard

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PaymentCardModule @Inject constructor(
    private val direction: PaymentCardDirection,
    private val repository: AppRepository,
    private val myShared: MyShared
) : ViewModel(), PaymentCardContract.Model {
    override fun onEventDispatcher(intent: PaymentCardContract.Intent) = intent {
        when (intent) {
            PaymentCardContract.Intent.PopBackStack -> {
                direction.PopBackStack()
            }
        }
    }

    override val container =
        container<PaymentCardContract.UIState, PaymentCardContract.SideEffect>(PaymentCardContract.UIState.InitState)

}