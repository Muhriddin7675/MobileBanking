package com.example.mobilebanking.presentantion.screen.pincode

import org.orbitmvi.orbit.ContainerHost

interface PinCreateContract {
    interface PinCheckScreenModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface UiState {
        data class Display(val text: String = "") : UiState
        data class PhoneNumber(val phone: String) : UiState
    }

    sealed interface Intent {
        data class ShowToast(val msg: String) : Intent
        data object ClickNextButton : Intent
        data object ClickBackButton : Intent
        data object SetPhoneNumber : Intent
        data class SetPinCode(val pinCode:String):Intent
    }

    sealed interface SideEffect {
        data class ShowToast(val msg: String) : SideEffect
    }
}