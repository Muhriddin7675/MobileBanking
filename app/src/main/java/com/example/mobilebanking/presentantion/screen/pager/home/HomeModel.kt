package com.example.mobilebanking.presentantion.screen.pager.home

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeModel @Inject constructor(
    private val direction: HomeDirection,
    private val repository: AppRepository
) : ViewModel(), HomeContract.Model {
    override fun onEventDispatcher(intent: HomeContract.Intent) = intent {
        when (intent) {
            HomeContract.Intent.OpenProfilScreen -> {
                direction.openProfilScreen()
            }

            HomeContract.Intent.OpenAddCardScreen -> {
                direction.openAddCardScreen()
            }

            HomeContract.Intent.OpenAddCardBottomDialog -> {
                postSideEffect(HomeContract.SideEffect.OpenAddCardBottomDialog)
            }

            HomeContract.Intent.OpenPaymentCardScreen -> {
                direction.openPaymentCardScreen()
            }

            HomeContract.Intent.OpenWhatIsPaymentCard -> {
                direction.openWhatIsPaymentCard()
            }

            HomeContract.Intent.OpenIdentityVerificationScreen -> {
                direction.openIdentityVerificationScreen()
            }
        }
    }

    override val container =
        container<HomeContract.UIState, HomeContract.SideEffect>(HomeContract.UIState.InitState)
}