package com.example.mobilebanking.presentantion.screen.paymentcard

import org.orbitmvi.orbit.ContainerHost

interface PaymentCardContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        data object PopBackStack : Intent
    }
}