package com.example.mobilebanking.presentantion.screen.transfer_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilebanking.domain.uscase.GetFeeUseCase
import com.example.mobilebanking.domain.uscase.TransferUseCase
import com.example.mobilebanking.util.MyDataLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TransferCardViewModel @Inject constructor(
    private val directions: TransferCardDirections,
    private val transfer: TransferUseCase,
    private val getFee: GetFeeUseCase
) : ViewModel(), TransferCardContract.ViewModel {
    override val container = container<TransferCardContract.UIState, TransferCardContract.SideEffect>(
        TransferCardContract.UIState.GetFee(
        "", ""
    ))

    override fun onEventDispatcher(intent: TransferCardContract.Intent) = intent {
        when(intent) {
            TransferCardContract.Intent.BackTransferScreen -> {
                directions.backTransferScreen()
            }
            is TransferCardContract.Intent.TransferMoney -> {
                transfer(intent.senderId, intent.receiverPan, intent.amount + intent.percent).onEach { result ->
                    result.onSuccess {
                        intent { directions.navigateTransferVerifyScreen(it.token, intent.data, intent.amount.toString()) }
                    }
                    result.onFailure {

                    }
                }.launchIn(viewModelScope)
            }
            is TransferCardContract.Intent.GetFeeOfTransfer -> {
                getFee(intent.senderId, intent.receiverPan, intent.amount).onEach { result ->
                    result.onSuccess { data ->
                       intent { reduce { TransferCardContract.UIState.GetFee(data.fee, data.amount) } }
                    }
                    result.onFailure {

                    }
                }.launchIn(viewModelScope)
            }
            TransferCardContract.Intent.LoadCardsData -> {
                MyDataLoader.listStateFlow.onEach {
                    intent {
                        reduce { TransferCardContract.UIState.SelectedCardData(it[0]) }
                    }

                }.launchIn(viewModelScope)
            }
            TransferCardContract.Intent.OpenSelectMyCardDialog -> {
                postSideEffect(TransferCardContract.SideEffect.OpenSelectMyCardDialog)
            }
            is TransferCardContract.Intent.SelectedCardData -> {
               reduce { TransferCardContract.UIState.SelectedCardData(intent.data) }
            }
            TransferCardContract.Intent.NavigateAddCardScreen -> {
                directions.navigateAddCardScreen()
            }
        }
    }


}