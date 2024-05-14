package com.example.mobilebanking.presentantion.screen.whatis

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WhatIsPaymentCardModule @Inject constructor(
    private val direction:WhatIsPaymentCardDirection ,
) :ViewModel(),WhatIsPaymentCardContract.Model{
    override fun onEventDispatcher(intent: WhatIsPaymentCardContract.Intent) = intent{
        when(intent){
            WhatIsPaymentCardContract.Intent.PopBackStack -> {
                direction.popBackStack()
            }

            WhatIsPaymentCardContract.Intent.OpenConditionScreen -> {
                direction.openConditionsScreen()
            }
        }
    }

    override val container  = container<WhatIsPaymentCardContract.UIState, WhatIsPaymentCardContract.SideEffect>(WhatIsPaymentCardContract.UIState.InitState)
}