package com.example.mobilebanking.screen.login

import RegisterScreenContract
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.mobilebanking.screen.comfirmation.ConfirmationContract
import com.example.mobilebanking.util.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class RegisterScreenModel @Inject constructor(
    val navigator: AppNavigator
) : RegisterScreenContract.RegisterScreenModel, ViewModel(), ScreenModel {
    override fun onEventDispatcher(intent: RegisterScreenContract.Intent) = intent {
        when (intent) {
            is RegisterScreenContract.Intent.ClickBackButton -> {

            }

            is RegisterScreenContract.Intent.ClickNextButton -> {

            }

            is RegisterScreenContract.Intent.ShowToast -> {
                postSideEffect(RegisterScreenContract.SideEffect.ShowToast(intent.msg))
            }

            else -> {}
        }
    }

    override val container =
        container<RegisterScreenContract.UiState, RegisterScreenContract.SideEffect>(
            RegisterScreenContract.UiState.Display("")
        )

}