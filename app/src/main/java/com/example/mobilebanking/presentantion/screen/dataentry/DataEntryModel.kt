package com.example.mobilebanking.presentantion.screen.dataentry

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DataEntryModel @Inject constructor(
    private val direction: DataEntryDirection,
    private val repository: AppRepository,
    private val myShared: MyShared
) : ViewModel(), DataEntryContract.Model {
    override fun onEventDispatcher(intent: DataEntryContract.Intent) = intent {
        when (intent) {
            DataEntryContract.Intent.PopBackStack -> {
                direction.popBackStack()
            }

            is DataEntryContract.Intent.SendPassportIdAndJshshir -> {

            }
        }
    }

    override val container =
        container<DataEntryContract.UIState, DataEntryContract.SideEffect>(DataEntryContract.UIState.InitState)
}