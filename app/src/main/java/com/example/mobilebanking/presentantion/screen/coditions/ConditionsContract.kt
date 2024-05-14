package com.example.mobilebanking.presentantion.screen.coditions

import org.orbitmvi.orbit.ContainerHost

interface ConditionsContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        data object OpenIdentityVerificationScreen : Intent

        data object PopBackStack : Intent
    }
}