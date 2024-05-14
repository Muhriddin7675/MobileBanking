package com.example.mobilebanking.presentantion.screen.identity

import org.orbitmvi.orbit.ContainerHost

interface IdentityVerificationContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        data object PopBackStack : Intent
    }
}