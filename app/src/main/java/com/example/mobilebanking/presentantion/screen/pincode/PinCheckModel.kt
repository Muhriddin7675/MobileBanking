package com.example.mobilebanking.presentantion.screen.pincode

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class PinCheckModel @Inject constructor(
    private val repository: AppRepository,
    private val direction: PinCheckDirection
) : ViewModel(), ScreenModel, PinCheckContract.PinCheckScreenModel {
    override fun onEventDispatcher(intent: PinCheckContract.Intent)  = intent{
        when(intent){
            PinCheckContract.Intent.ClickBackButton -> {

            }
            is PinCheckContract.Intent.ClickNextButton -> {
                direction.openMainScreen()
            }
            is PinCheckContract.Intent.ShowToast -> {
                postSideEffect(PinCheckContract.SideEffect.ShowToast(intent.msg))

            }
        }
    }

    override val container = container<PinCheckContract.UiState, PinCheckContract.SideEffect>(PinCheckContract.UiState.Display(""))
}