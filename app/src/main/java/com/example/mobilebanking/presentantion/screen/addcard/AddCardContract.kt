package com.example.mobilebanking.presentantion.screen.addcard

import org.orbitmvi.orbit.ContainerHost

interface AddCardContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        data class AddCard(val cardNumber: String, val expiryDate: String) : Intent

        data object PopBackStack : Intent
    }
}