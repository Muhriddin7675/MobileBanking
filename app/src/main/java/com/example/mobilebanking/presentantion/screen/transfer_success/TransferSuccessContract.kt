package com.example.mobilebanking.presentantion.screen.transfer_success

import org.orbitmvi.orbit.ContainerHost

interface TransferSuccessContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    class UIState

    sealed interface SideEffect

    sealed interface Intent {
        data object BackMainScreen : Intent
    }
}