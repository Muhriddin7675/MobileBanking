package com.example.mobilebanking.presentantion.screen.comfirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.repositori.AppRepository
import com.example.mobilebanking.util.myLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ConfirmationModel @Inject constructor(
    val direction: ConfirmDirection,
    val repository: AppRepository,
    val myShared: MyShared
) : ViewModel(), ConfirmationContract.ConfirmationModel, ScreenModel {

    override val container =
        container<ConfirmationContract.UiState, ConfirmationContract.SideEffect>(
            ConfirmationContract.UiState.Display("")
        )
    override fun onEventDispatcher(intent: ConfirmationContract.Intent) = intent {
        when (intent) {
            ConfirmationContract.Intent.ClickBackButton -> {

            }

            is ConfirmationContract.Intent.ClickButton -> {
                val code = intent.code
                if(myShared.getSignInSignUpState()){
                    repository.signInVerify(code)
                        .onEach {
                            it.onSuccess {
                                direction.openPinCodeScreen()
                            }
                            it.onFailure {
                                myLog("ConfirmationContract.Intent.ClickButton signInVerify onFailure")
                            }
                        }
                        .launchIn(viewModelScope)
                }
                else{
                    repository.signUpVerify(code)
                        .onEach {
                            it.onSuccess {
                                direction.openPinCodeScreen()
                            }
                            it.onFailure {
                                myLog("ConfirmationContract.Intent.ClickButton signUpVerify  onFailure")
                            }
                        }
                        .launchIn(viewModelScope)
                }
            }

            is ConfirmationContract.Intent.ShowToast -> {
                postSideEffect(ConfirmationContract.SideEffect.ShowToast(intent.msg))
            }
        }
    }


}