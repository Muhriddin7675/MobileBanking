package com.example.mobilebanking.presentantion.screen.register

import RegisterScreenContract
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class RegisterScreenModel @Inject constructor(
    private val repository: AppRepository,
    private val direction: RegisterDirection,
    private val myShared: MyShared
) : RegisterScreenContract.RegisterScreenModel, ViewModel(), ScreenModel {

    fun getLanguageState(): Boolean = myShared.getLanguage()

    override fun onEventDispatcher(intent: RegisterScreenContract.Intent) = intent {
        when (intent) {

            is RegisterScreenContract.Intent.ClickNextButton -> {
                val password = intent.phoneNumber
                val phone = "+998$password"
                myShared.setPassword(password)
                direction.openConfirmationScreen()
         /*       repository.signIn(phone = phone, password = password).onEach {
                    it.onSuccess {
                        myShared.setSingInSignUpState(true)
                        direction.openConfirmationScreen()
                    }.onFailure { error ->
                        myLog(error.message.toString())
                        repository.signUp(
                            SignUpUserDataRequest(
                                phone = phone,
                                password = password,
                                firstName = "Muhriddin",
                                lastName = "Valiyev",
                                bornDate = "969822000000",
                                gender = "0"
                            )
                        ).onEach {
                            it.onSuccess {
                                myShared.setSingInSignUpState(false)
                                direction.openConfirmationScreen()
                            }.onFailure { error ->
                                myLog(error.message.toString())
                            }
                        }.launchIn(viewModelScope)
                    }
                }.launchIn(viewModelScope)*/
            }

            is RegisterScreenContract.Intent.ShowToast -> {
                postSideEffect(RegisterScreenContract.SideEffect.ShowToast(intent.msg))
            }

            is RegisterScreenContract.Intent.OpenLanguageDialog -> {
                postSideEffect(RegisterScreenContract.SideEffect.OpenLanguageDialog(myShared.getLanguage()))
            }

            is RegisterScreenContract.Intent.SetLanguageState -> {
                myShared.setLanguage(intent.bool)
            }

            is RegisterScreenContract.Intent.SetUiLanguageState -> {
                reduce {
                    RegisterScreenContract.UiState.LanguageState(myShared.getLanguage())
                }
            }

            else -> {}
        }
    }

    override val container =
        container<RegisterScreenContract.UiState, RegisterScreenContract.SideEffect>(
            RegisterScreenContract.UiState.Display("")
        )

}