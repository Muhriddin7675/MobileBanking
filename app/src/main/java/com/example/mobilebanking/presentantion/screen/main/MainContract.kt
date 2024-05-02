package com.example.mobilebanking.presentantion.screen.main

import org.orbitmvi.orbit.ContainerHost

sealed interface MainContract {

    sealed interface Model : ContainerHost<UIState, SideEffect>{
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState: UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        object LogOut : Intent
    }
}


