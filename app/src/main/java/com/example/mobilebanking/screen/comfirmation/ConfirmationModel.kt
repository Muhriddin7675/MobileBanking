package com.example.mobilebanking.screen.comfirmation

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.mobilebanking.screen.pager.main.MainScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ConfirmationModel @Inject constructor(
    val navigator: AppNavigator
) : ViewModel(), ConfirmationContract.ConfirmationModel, ScreenModel {

    override val container = container<ConfirmationContract.UiState, ConfirmationContract.SideEffect>(
        ConfirmationContract.UiState.Display("")
    )

    override fun onEventDispatcher(intent: ConfirmationContract.Intent) = intent {
        when (intent) {
            ConfirmationContract.Intent.ClickBackButton -> {
                navigator.back()
            }

            is ConfirmationContract.Intent.ClickButton -> {
//                navigator.replace(PinScreen())
                navigator.replace(MainScreen())
            }

            is ConfirmationContract.Intent.ShowToast -> {
                postSideEffect(ConfirmationContract.SideEffect.ShowToast(intent.msg))
            }
        }
    }


}