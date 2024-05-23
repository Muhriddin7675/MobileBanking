package com.example.mobilebanking.presentantion.screen.allcard

import com.example.mobilebanking.data.model.CardData
import org.orbitmvi.orbit.ContainerHost

class AllCardContract {
    sealed interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data object InitState : UIState
        data class SetCardList(val cardList:List<CardData>):UIState
    }

    sealed interface SideEffect {
        data object OpenAddCardBottomDialog : SideEffect
    }

    sealed interface Intent {
        data class OpenCardDetailScreen(val data: CardData) : Intent
        data object PopBackStack : Intent
        data object OpenAddCardBottomDialog : Intent
        data object SetCardList : Intent
        data object OpenAddCardScreen:Intent
    }
}