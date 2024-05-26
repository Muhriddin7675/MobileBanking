package com.example.mobilebanking.presentantion.screen.transfer_verify

import com.example.mobilebanking.data.model.UserCardData
import org.orbitmvi.orbit.ContainerHost

interface TransferVerifyContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data class RequestToken(
            var token: String
        ) : UIState
        data object ErrorMes : UIState
        data class UserPhoneNumber(
            var phone: String
        ) : UIState
    }

    sealed interface SideEffect

    sealed interface Intent{
        data class NavigateTransferSuccessScreen(
            val token: String,
            val code: String,
            val data: UserCardData,
            val amount: String
        ) : Intent
        data object BackToTransferCardScreen : Intent
        data object GetData : Intent
        data class ResendCode (
            val token: String
        ) : Intent
    }
}