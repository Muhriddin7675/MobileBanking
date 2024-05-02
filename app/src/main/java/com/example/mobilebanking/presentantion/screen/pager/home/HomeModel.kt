package com.example.mobilebanking.presentantion.screen.pager.home

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeModel @Inject constructor(
    private val direction: HomeDirection,
    private val repository: AppRepository
):ViewModel(),HomeContract.Model {
    override fun onEventDispatcher(intent: HomeContract.Intent) {
        TODO("Not yet implemented")
    }

    override val container = container<HomeContract.UIState, HomeContract.SideEffect>(HomeContract.UIState.InitState)
}