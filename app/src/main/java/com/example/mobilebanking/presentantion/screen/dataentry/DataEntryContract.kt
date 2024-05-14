package com.example.mobilebanking.presentantion.screen.dataentry

import org.orbitmvi.orbit.ContainerHost

interface DataEntryContract {

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