package com.example.mobilebanking.presentantion.screen.transfer_verify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilebanking.data.local.entity.LastTransferUserEntity
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.repositori.LocalRepository
import com.example.mobilebanking.domain.uscase.TransferResendUseCase
import com.example.mobilebanking.domain.uscase.TransferVerifyUseCase
import com.example.mobilebanking.util.MyDataLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TransferVerifyViewModel @Inject constructor(
    private val directions: TransferVerifyDirections,
    private val repo: LocalRepository,
    private val resendCode: TransferResendUseCase,
    private val transferVerify: TransferVerifyUseCase,
    private val myShared: MyShared
) : ViewModel(), TransferVerifyContract.ViewModel {
    override fun onEventDispatcher(intent: TransferVerifyContract.Intent) = intent {
        when(intent) {
            TransferVerifyContract.Intent.BackToTransferCardScreen -> {
                directions.backToTransferCardScreen()
            }
            TransferVerifyContract.Intent.GetData -> {
                reduce { TransferVerifyContract.UIState.UserPhoneNumber("+998${myShared.getPassword()}") }
            }
            is TransferVerifyContract.Intent.ResendCode -> {
                resendCode(intent.token).onEach { result ->
                    result.onSuccess {
                        intent { reduce { TransferVerifyContract.UIState.RequestToken(it.token) } }
                    }
                    result.onFailure {}
                }.launchIn(viewModelScope)
            }
            is TransferVerifyContract.Intent.NavigateTransferSuccessScreen -> {
                transferVerify(intent.token, intent.code).onEach { result ->
                    result.onSuccess {
                        intent{
                            MyDataLoader.loadCardsData()
                            repo.addUser(
                                LastTransferUserEntity(
                                    intent.data.pan,
                                    intent.data.owner
                                )
                            )
                        }
                        intent{ directions.navigateTransferSuccessScreen(intent.data, intent.amount) }
                    }
                    result.onFailure {
                        intent { reduce { TransferVerifyContract.UIState.ErrorMes } }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    override val container = container<TransferVerifyContract.UIState, TransferVerifyContract.SideEffect>(
        TransferVerifyContract.UIState.UserPhoneNumber("")
    )

}