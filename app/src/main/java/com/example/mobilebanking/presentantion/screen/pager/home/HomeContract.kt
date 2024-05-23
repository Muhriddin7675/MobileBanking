package com.example.mobilebanking.presentantion.screen.pager.home

import com.example.mobilebanking.data.model.CardData
import org.orbitmvi.orbit.ContainerHost

interface HomeContract {

    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data class GetUIState(
            val phone: String = "+998900993936",
            val showBalance: Boolean = true,
        ) : UIState
        data class LoadCardData(
            val totalAmount:Long = 0,
            val cardList: List<CardData>) : UIState

    }

    sealed interface SideEffect {
        data object OpenAddCardBottomDialog : SideEffect
    }

    sealed interface Intent {
        data class ChangeShowBalanceState(
            var showBalance: Boolean
        ) : Intent
        data object OpenAddCardBottomDialog : Intent
        data object OpenAddCardScreen : Intent
        data object OpenProfilScreen : Intent
        data object OpenPaymentCardScreen : Intent
        data object OpenWhatIsPaymentCard : Intent
        data object OpenIdentityVerificationScreen : Intent
        data object GetAllCard : Intent
        data object OpenAllCardScreen : Intent
        data object GetData :Intent

        data class OpenCardDetailScreen(val data: CardData):Intent


    }
}