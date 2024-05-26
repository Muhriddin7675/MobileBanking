package uz.gita.mobilebanking.presentation.pin

import org.orbitmvi.orbit.ContainerHost

interface PinContract {
    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent : Intent)
    }

    data class UIState(
        val pinCode : String,
        val phoneNumber : String,
        val biometricSt:Boolean
    )
    sealed interface Intent {
        data object ToMainScreen  : Intent
    }
    sealed interface SideEffect
}