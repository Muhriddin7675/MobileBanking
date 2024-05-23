package com.example.mobilebank.presentation.screens.card_details

import com.example.mobilebanking.data.model.CardData
import org.orbitmvi.orbit.ContainerHost

interface CardDetailsContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    class UIState

    sealed interface SideEffect {
        data object NavigateToTransferPage : SideEffect
        data object NavigateToPaymentPage : SideEffect
    }

    sealed interface Intent {
        data object BackToHomeScreen : Intent
        data class DeleteCard(val id: String) : Intent
        data class NavigateToCardTheme(
            val data: CardData
        ) : Intent
        data object NavigateToTransferPage : Intent
        data object NavigateToPaymentPage : Intent
    }
}