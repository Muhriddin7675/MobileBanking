package com.example.mobilebanking.presentantion.screen.pager.home

import org.orbitmvi.orbit.ContainerHost

interface HomeContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data object InitState : UIState
    }

    sealed interface SideEffect {
        data object OpenAddCardBottomDialog : SideEffect
    }

    sealed interface Intent {
        data object OpenAddCardBottomDialog : Intent
        data object OpenAddCardScreen : Intent
        data object OpenProfilScreen : Intent
        data object OpenPaymentCardScreen : Intent
        data object OpenWhatIsPaymentCard : Intent
        data object OpenIdentityVerificationScreen : Intent
    }
}