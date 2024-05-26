package com.example.mobilebank.presentation.screens.transfer_success

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.presentantion.screen.transfer_success.TransferSuccessContract
import com.example.mobilebanking.presentantion.screen.transfer_success.TransferSuccessDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TransferSuccessViewModel @Inject constructor(
    private val directions: TransferSuccessDirections
) : ViewModel(), TransferSuccessContract.ViewModel {
    override val container = container<TransferSuccessContract.UIState, TransferSuccessContract.SideEffect>(
        TransferSuccessContract.UIState())

    override fun onEventDispatcher(intent: TransferSuccessContract.Intent) = intent {
        when(intent) {
            TransferSuccessContract.Intent.BackMainScreen -> {
                directions.backMainScreen()
            }
        }
    }

}