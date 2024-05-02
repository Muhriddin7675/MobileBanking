package com.example.mobilebanking.presentantion.screen.pincode

import org.orbitmvi.orbit.ContainerHost

interface PinCheckContract {
    interface PinCheckScreenModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface UiState {
        data class Display(val text: String = "") : UiState
    }

    sealed interface Intent {
        data class ShowToast(val msg: String) : Intent
        data object ClickNextButton : Intent
        data object ClickBackButton : Intent
    }

    sealed interface SideEffect {
        data class ShowToast(val msg: String) : SideEffect
    }
}