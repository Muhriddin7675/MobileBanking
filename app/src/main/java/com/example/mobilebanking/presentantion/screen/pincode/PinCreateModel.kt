package com.example.mobilebanking.presentantion.screen.pincode

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
class PinCreateModel @Inject constructor(
    private val repository: AppRepository,
    private val direction: PinCheckDirection,
    private val myShared: MyShared

) : ViewModel(), ScreenModel, PinCreateContract.PinCheckScreenModel {
    override fun onEventDispatcher(intent: PinCreateContract.Intent) = intent {
        when (intent) {
            PinCreateContract.Intent.ClickBackButton -> {

            }

            is PinCreateContract.Intent.ClickNextButton -> {
                direction.openMainScreen()
            }

            is PinCreateContract.Intent.ShowToast -> {
                postSideEffect(PinCreateContract.SideEffect.ShowToast(intent.msg))

            }

            is PinCreateContract.Intent.SetPhoneNumber -> {
                reduce {
                    val number = myShared.getPassword()
                    val phoneNumber =
                        "+998 " + number.substring(0, 2) + " ... .. " + number.substring(7)
                    PinCreateContract.UiState.PhoneNumber(phoneNumber)
                }
            }
            is PinCreateContract.Intent.SetPinCode ->{
                myShared.setPinCode(intent.pinCode)
            }

        }
    }

    override val container = container<PinCreateContract.UiState, PinCreateContract.SideEffect>(
        PinCreateContract.UiState.Display("")
    )
}