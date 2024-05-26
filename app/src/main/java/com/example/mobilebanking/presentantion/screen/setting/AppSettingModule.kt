package com.example.mobilebanking.presentantion.screen.setting

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.repositori.AppRepository
import com.example.mobilebanking.util.getCurrentLanguage
import com.example.mobilebanking.util.myLog
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AppSettingModule @Inject constructor(
    private val direction: AppSettingDirection,
    private val repository: AppRepository,
    private val myShared: MyShared
) : ViewModel(), AppSettingContract.Model {
    override fun onEventDispatcher(intent: AppSettingContract.Intent) = intent {
        when (intent) {
            AppSettingContract.Intent.PopBackStack -> {
                direction.PopBackStack()
            }

            AppSettingContract.Intent.LoadAppSettings -> {
                reduce {
                    myLog(myShared.getBiometryUnlock().toString() + " biometrick state")
                    AppSettingContract.UIState(
                        getCurrentLanguage() == "uz",
                        myShared.getBiometryUnlock()
                    )
                }
            }

            is AppSettingContract.Intent.SetBiometryUnlock -> {
                myShared.setBiometryUnlock(intent.biometry)
            }
        }
    }

    override val container =
        container<AppSettingContract.UIState, AppSettingContract.SideEffect>(AppSettingContract.UIState(true,true))

}