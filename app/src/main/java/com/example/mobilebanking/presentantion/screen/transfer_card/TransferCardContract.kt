package com.example.mobilebanking.presentantion.screen.transfer_card

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.model.UserCardData
import org.orbitmvi.orbit.ContainerHost

interface TransferCardContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
    sealed interface UIState {
        data class GetFee(
            val fee: String = "",
            val amount: String = "0"
        ) : UIState
        data class SelectedCardData(
            val data: CardData
        ) : UIState
    }

    sealed interface SideEffect {
        data object OpenSelectMyCardDialog : SideEffect
    }

    sealed interface Intent {
        data object BackTransferScreen : Intent
        data object NavigateAddCardScreen : Intent
        data object OpenSelectMyCardDialog : Intent
        data object LoadCardsData : Intent
        data class SelectedCardData(
            val data: CardData
        ) : Intent
        data class GetFeeOfTransfer(
            val senderId: String,
            val receiverPan: String,
            val amount: Long
        ) : Intent
        data class TransferMoney(
            val senderId: String,
            val receiverPan: String,
            val amount: Long,
            val percent: Long,
            val data: UserCardData
        ) : Intent
    }
}