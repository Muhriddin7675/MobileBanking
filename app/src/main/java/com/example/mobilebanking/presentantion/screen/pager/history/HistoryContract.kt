package com.example.mobilebanking.presentantion.screen.pager.history

import androidx.paging.PagingData
import com.example.mobilebanking.data.remote.response.Child
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.ContainerHost

interface HistoryContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
        fun getCardHistory() : Flow<PagingData<Child>>
    }

    class UIState

    sealed interface SideEffect

    sealed interface Intent {
        data object GetHistory : Intent
    }
}