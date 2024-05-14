package com.example.mobilebanking.presentantion.screen.identity

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class IdentityVerificationModel @Inject constructor(
    private val direction: IdentityVerificationDirection,
    private val appRepository: AppRepository,
    private val myShared: MyShared
) : ViewModel(), IdentityVerificationContract.Model {
    override fun onEventDispatcher(intent: IdentityVerificationContract.Intent) = intent {
        when (intent) {
            IdentityVerificationContract.Intent.PopBackStack -> {
                direction.popBackStack()
            }
        }
    }

    override val container =
        container<IdentityVerificationContract.UIState, IdentityVerificationContract.SideEffect>(
            IdentityVerificationContract.UIState.InitState
        )
}