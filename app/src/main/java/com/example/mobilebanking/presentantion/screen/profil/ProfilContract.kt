package com.example.mobilebanking.presentantion.screen.profil

import org.orbitmvi.orbit.ContainerHost

interface ProfilContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
      data object InitState : UIState
    }

    sealed interface SideEffect {
        data object OpenPaymentAbout:SideEffect
        data object OpenReference:SideEffect
        data object OpenRite:SideEffect
    }

    sealed interface Intent {
        data object OpenPaymentAbout:Intent
        data object OpenReference:Intent
        data object OpenRite:Intent

        data object OpenMapScreen : Intent
        data object PopBackStack:Intent
        data object LogOut : Intent
        data object OpenSettingScreen:Intent

    }
}