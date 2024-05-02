package com.example.mobilebanking.presentantion.screen.pager.payment

import org.orbitmvi.orbit.ContainerHost

sealed interface PaymentContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {

    }
}


