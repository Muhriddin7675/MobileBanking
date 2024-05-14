package com.example.mobilebanking.presentantion.screen.setting

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AppSettingModule @Inject constructor(
    private val direction: AppSettingDirection,
    private val repository: AppRepository,
    private val myShared: MyShared
):ViewModel(),AppSettingContract.Model{
    override fun onEventDispatcher(intent: AppSettingContract.Intent)  = intent{
       when(intent){
           AppSettingContract.Intent.PopBackStack -> {
               direction.PopBackStack()
           }
       }
    }

    override val container = container<AppSettingContract.UIState, AppSettingContract.SideEffect>(AppSettingContract.UIState.InitState)

}