package com.example.mobilebanking.presentantion.screen.network

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.util.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class NoConnectionViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel(), NoConnectionContract.ViewModel {
    override val container = container<NoConnectionContract.UIState, NoConnectionContract.SideEffect>(
        NoConnectionContract.UIState())

    override fun onEventDispatcher(intent: NoConnectionContract.Intent) = intent {
        when(intent) {
            NoConnectionContract.Intent.Back -> {
                appNavigator.back()
            }
            NoConnectionContract.Intent.Refresh -> {
                postSideEffect(NoConnectionContract.SideEffect.Refresh)
            }
        }
    }


}