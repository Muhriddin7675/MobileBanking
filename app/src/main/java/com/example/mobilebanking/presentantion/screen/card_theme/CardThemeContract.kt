package com.example.mobilebank.presentation.screens.card_theme

import com.example.mobilebanking.data.model.CardData
import org.orbitmvi.orbit.ContainerHost

interface CardThemeContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
    class UIState

    sealed interface SideEffect

    sealed interface Intent {
        data class UpdateCard(
            val data: CardData
        ) : Intent
        data class BackCardDetails(
            val data: CardData
        ) : Intent
    }
}