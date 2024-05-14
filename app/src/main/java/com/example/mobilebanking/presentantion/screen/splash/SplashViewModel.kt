package com.example.mobilebanking.presentantion.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val direction: SplashDirection,
    private val repository: AppRepository,
    private val myShared: MyShared
) : ViewModel() {

    fun onEventDispatcher(intent: SplashIntent) {
        when (intent) {
            SplashIntent.Intro -> {
                viewModelScope.launch {
                    delay(1500)
                    if (myShared.getPassword() == "password")
                        direction.openRegisterScreen()
                    else direction.openPinScreen()
                }
            }
        }
    }
}
