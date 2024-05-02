package com.example.mobilebanking.presentantion.screen.comfirmation

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ConfirmationModel @Inject constructor(
   val direction: ConfirmDirection
) : ViewModel(), ConfirmationContract.ConfirmationModel, ScreenModel {

    override val container = container<ConfirmationContract.UiState, ConfirmationContract.SideEffect>(
        ConfirmationContract.UiState.Display("")
    )

    override fun onEventDispatcher(intent: ConfirmationContract.Intent) = intent {
        when (intent) {
            ConfirmationContract.Intent.ClickBackButton -> {

            }

            is ConfirmationContract.Intent.ClickButton -> {
                direction.openPinCodeScreen()
            }

            is ConfirmationContract.Intent.ShowToast -> {
                postSideEffect(ConfirmationContract.SideEffect.ShowToast(intent.msg))
            }
        }
    }


}