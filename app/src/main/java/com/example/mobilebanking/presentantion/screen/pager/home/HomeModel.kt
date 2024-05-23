package com.example.mobilebanking.presentantion.screen.pager.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.AppRepository
import com.example.mobilebanking.domain.CardRepository
import com.example.mobilebanking.util.MyDataLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeModel @Inject constructor(
    private val direction: HomeDirection,
    private val repository: AppRepository,
    private val cardRepository: CardRepository,
    private val myShared: MyShared
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


            is HomeContract.Intent.OpenCardDetailScreen -> {
                direction.openCardDetailScreen(intent.data)
            }

            HomeContract.Intent.OpenAllCardScreen -> {
                direction.openAllCardScreen()
            }

            is HomeContract.Intent.ChangeShowBalanceState -> {
                myShared.changeShowBalanceState(intent.showBalance)
            }
            HomeContract.Intent.GetData -> {
                reduce { HomeContract.UIState.GetUIState(myShared.getPassword(), myShared.showBalance()) }
            }
            HomeContract.Intent.GetAllCard -> {
                MyDataLoader.listStateFlow.onEach { list ->
                    MyDataLoader.totalAmountStateFlow.onEach { amount ->
                        intent {
                            reduce {
                                HomeContract.UIState.LoadCardData(amount, list)
                            }
                        }
                    }.launchIn(viewModelScope)
                }.launchIn(viewModelScope)
            }
        }
    }

    override val container =
        container<HomeContract.UIState, HomeContract.SideEffect>(HomeContract.UIState.GetUIState())
}