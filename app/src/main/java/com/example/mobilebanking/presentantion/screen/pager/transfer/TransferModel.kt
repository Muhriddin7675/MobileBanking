package com.example.mobilebanking.presentantion.screen.pager.transfer

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TransferModel @Inject constructor(
    private val direction: TransferDirection,
    private val repository: AppRepository
):ViewModel(),TransferContract.Model{
    override fun onEventDispatcher(intent: TransferContract.Intent) {
    }

    override val container = container<TransferContract.UIState, TransferContract.SideEffect>(TransferContract.UIState.InitState)

}