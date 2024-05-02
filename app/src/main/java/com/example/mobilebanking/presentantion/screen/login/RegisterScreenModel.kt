package com.example.mobilebanking.presentantion.screen.login

import RegisterScreenContract
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.mobilebanking.util.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class RegisterScreenModel @Inject constructor(
    private val navigator: AppNavigator,
    private val direction: RegisterDirection
) : RegisterScreenContract.RegisterScreenModel, ViewModel(), ScreenModel {
    override fun onEventDispatcher(intent: RegisterScreenContract.Intent) = intent {
        when (intent) {
            is RegisterScreenContract.Intent.ClickBackButton -> {

            }
            is RegisterScreenContract.Intent.ClickNextButton -> {
                direction.openConfirmationScreen()
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