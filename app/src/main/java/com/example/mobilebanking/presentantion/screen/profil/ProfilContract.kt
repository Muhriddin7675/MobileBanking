package com.example.mobilebanking.presentantion.screen.profil

import org.orbitmvi.orbit.ContainerHost

interface ProfilContract {

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