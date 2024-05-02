package com.example.mobilebanking.presentantion.screen.pager.transfer

import org.orbitmvi.orbit.ContainerHost

interface TransferContract {
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