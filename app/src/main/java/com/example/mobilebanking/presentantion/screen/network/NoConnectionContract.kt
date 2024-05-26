package com.example.mobilebanking.presentantion.screen.network

import org.orbitmvi.orbit.ContainerHost

interface NoConnectionContract {
    sealed interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface Intent {
        data object Back : Intent
        data object Refresh : Intent
    }

    class UIState
    sealed interface SideEffect {
        data object Refresh : SideEffect
    }
}