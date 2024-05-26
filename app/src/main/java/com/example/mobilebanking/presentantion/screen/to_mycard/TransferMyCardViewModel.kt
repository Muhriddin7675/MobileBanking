package com.example.mobilebanking.presentantion.screen.to_mycard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilebanking.domain.uscase.TransferUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TransferMyCardViewModel @Inject constructor(
    private val transfer: TransferUseCase,
    private val directions: TransferMyCardDirections
) : ViewModel(), TransferMyCardContract.ViewModel {
    override val container = container<TransferMyCardContract.UIState, TransferMyCardContract.SideEffect>(
        TransferMyCardContract.UIState.SelectedCardTo())

    override fun onEventDispatcher(intent: TransferMyCardContract.Intent) = intent {
        when(intent) {
            is TransferMyCardContract.Intent.OpenSelectMyCardDialog -> {
                postSideEffect(TransferMyCardContract.SideEffect.OpenSelectMyCardDialog(intent.list, intent.type))
            }
            TransferMyCardContract.Intent.BackTransferScreen -> {
                directions.backTransferScreen()
            }
            is TransferMyCardContract.Intent.TransferMoney -> {
                transfer(intent.senderId, intent.receiverPan, intent.amount + intent.percent).onEach { result ->
                    result.onSuccess {
                        intent { directions.navigateTransferVerifyScreen(it.token, intent.data, intent.amount.toString()) }
                    }
                    result.onFailure {

                    }
                }.launchIn(viewModelScope)
            }
            is TransferMyCardContract.Intent.SelectedItem -> {
                if (intent.type == 0) {
                    reduce { TransferMyCardContract.UIState.SelectedCardTo(intent.data) }
                } else {
                    reduce { TransferMyCardContract.UIState.SelectedCardFrom(intent.data) }
                }
            }
        }
    }

}