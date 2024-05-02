package com.example.mobilebanking.presentantion.screen.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainModel @Inject constructor(
    private val direction: MainDirection
) : ViewModel(), MainContract.Model {
    override val container = container<MainContract.UIState, MainContract.SideEffect>(MainContract.UIState.InitState)

    override fun onEventDispatcher(intent: MainContract.Intent) {

    }
}


