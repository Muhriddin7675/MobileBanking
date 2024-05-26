package com.example.mobilebanking.presentantion.screen.pager.transfer

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.model.UserCardData
import org.orbitmvi.orbit.ContainerHost

interface TransferContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data class SearchedItems(
            val ls: List<UserCardData> = listOf()
        ) : UIState
        data class ErrorMes(
            val mes: String
        ) : UIState
        data class RemoteSearchItem(
            val data: UserCardData
        ) : UIState
        data class MyCardsData(
            val ls: List<CardData> = listOf()
        ) : UIState
    }

    sealed interface SideEffect {
        data object OpenDeleteAllUsersDialog : SideEffect
        data object OpenMyCardsDialog : SideEffect
    }
    interface Intent {
        data object OpenDeleteAllUsersDialog : Intent
        data object DeleteAll : Intent
        data class GetCardOwner(
            val pan: String
        ) : Intent
        data class SearchFromLocal(
            val pan: String
        ) : Intent
        data class NavigateTransferCardScreen(
            val userCardData: UserCardData
        ) : Intent
        data object LoadMyCardsData : Intent
        data object OpenMyCardsDialog : Intent
        data object NavigateAddCardScreen : Intent
        data class NavigateTransferMyCardScreen(
            val data: UserCardData
        ) : Intent
    }
}