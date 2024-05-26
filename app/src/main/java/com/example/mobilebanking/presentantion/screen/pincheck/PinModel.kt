package com.example.mobilebanking.presentantion.screen.pincheck

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.repositori.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.mobilebanking.presentation.pin.PinContract
import javax.inject.Inject

@HiltViewModel
class PinModel @Inject constructor(
    private val pinDirection: PinDirection,
    private val repository: AppRepository,
    private val myShared: MyShared
) : ViewModel(), PinContract.Model {
    override fun onEventDispatcher(intent: PinContract.Intent) {
        when (intent) {
            is PinContract.Intent.ToMainScreen -> {
                intent { pinDirection.toBottomNavigation() }
            }
            else ->{

            }
        }
    }

    override val container = container<PinContract.UIState, PinContract.SideEffect>(
        PinContract.UIState(
            pinCode = myShared.getPinCode(),
            phoneNumber = myShared.getPassword(),
            biometricSt = myShared.getBiometryUnlock()
        )
    )
}