package com.example.mobilebanking.presentantion.screen.comfirmation

import org.orbitmvi.orbit.ContainerHost


interface ConfirmationContract {
    interface ConfirmationModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface UiState {
        data class Display(val text: String = "") : UiState
    }

    sealed interface Intent {
        data class ShowToast(val msg: String) : Intent
        data class ClickButton(val code:String) : Intent
        data object ClickBackButton : Intent
    }

    sealed interface SideEffect {
        data class ShowToast(val msg: String) : SideEffect
    }

}