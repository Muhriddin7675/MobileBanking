package com.example.mobilebanking.presentantion.screen.pager.history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HistoryModel @Inject constructor(
    private val direction: HistoryDirection
) : ViewModel(), HistoryContract.Model {

    override val container = container<HistoryContract.UIState, HistoryContract.SideEffect>(
        HistoryContract.UIState.InitState
    )

    override fun onEventDispatcher(intent: HistoryContract.Intent) {

    }
}


