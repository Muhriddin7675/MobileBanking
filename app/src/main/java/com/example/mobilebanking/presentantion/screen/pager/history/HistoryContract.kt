package com.example.mobilebanking.presentantion.screen.pager.history

import org.orbitmvi.orbit.ContainerHost

sealed interface HistoryContract {
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


