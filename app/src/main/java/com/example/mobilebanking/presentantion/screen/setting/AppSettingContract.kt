package com.example.mobilebanking.presentantion.screen.setting

import org.orbitmvi.orbit.ContainerHost

interface AppSettingContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val lang: Boolean,
        val biometry: Boolean
    )

    sealed interface SideEffect

    sealed interface Intent {
        data object PopBackStack:Intent
        data object LoadAppSettings : Intent
        data class SetBiometryUnlock (
            val biometry: Boolean
        ) : Intent

    }
}