package com.example.mobilebanking.presentantion.screen.pager.home

import org.orbitmvi.orbit.ContainerHost

interface HomeContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        object OpenDetailScreen : Intent
    }
}