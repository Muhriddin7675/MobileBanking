package com.example.mobilebanking.presentantion.screen.coditions

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ConditionsModel @Inject constructor(
    private val direction: ConditionsDirection,
) :ViewModel(),ConditionsContract.Model{
    override fun onEventDispatcher(intent: ConditionsContract.Intent)  = intent{
        when(intent){
            ConditionsContract.Intent.PopBackStack -> {
                direction.popBackStack()
            }

            ConditionsContract.Intent.OpenIdentityVerificationScreen -> {
                direction.openIdentityScreen()
            }
        }
    }

    override val container = container<ConditionsContract.UIState, ConditionsContract.SideEffect>(ConditionsContract.UIState.InitState)


}