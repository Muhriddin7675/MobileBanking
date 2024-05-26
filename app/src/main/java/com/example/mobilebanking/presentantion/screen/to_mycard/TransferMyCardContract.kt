package com.example.mobilebanking.presentantion.screen.to_mycard

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.model.UserCardData
import org.orbitmvi.orbit.ContainerHost

interface TransferMyCardContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        data class SelectedCardTo (
            val data: CardData = CardData(-1, "", 0, "", "", "0", 0, 0, false)
        ) : UIState

        data class SelectedCardFrom (
            val data: CardData = CardData(-1, "", 0, "", "", "0", 0, 0, false)
        ) : UIState
    }

    sealed interface SideEffect {
        data class OpenSelectMyCardDialog(
            val list: List<CardData>,
            val type: Int
        ) : SideEffect
    }

    sealed interface Intent {
        data class OpenSelectMyCardDialog(
            val list: List<CardData>,
            val type: Int
        ) : Intent
        data class SelectedItem(
            val data: CardData,
            val type: Int
        ) : Intent

        data object BackTransferScreen : Intent
        data class TransferMoney(
            val senderId: String,
            val receiverPan: String,
            val amount: Long,
            val percent: Long,
            val data: UserCardData
        ) : Intent
    }
}