package com.example.mobilebanking.presentantion.screen.allcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilebanking.domain.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AllCardModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val direction: AllCardDirection
) : ViewModel(), AllCardContract.Model {


    override fun onEventDispatcher(intent: AllCardContract.Intent) = intent {
        when (intent) {
            AllCardContract.Intent.PopBackStack ->
                direction.popBackStack()

            is AllCardContract.Intent.OpenCardDetailScreen -> {
                direction.openCardDetail(intent.data)
            }

            AllCardContract.Intent.OpenAddCardBottomDialog -> {
                postSideEffect(AllCardContract.SideEffect.OpenAddCardBottomDialog)
            }

            AllCardContract.Intent.SetCardList -> {

                cardRepository.getCards().onEach {
                    it.onFailure {
                        reduce { AllCardContract.UIState.SetCardList(emptyList()) }
                    }
                    it.onSuccess {
                        reduce { AllCardContract.UIState.SetCardList(it) }
                    }
                }.launchIn(viewModelScope)
            }

            AllCardContract.Intent.OpenAddCardScreen -> {
                direction.openAddCardScreen()
            }
        }
    }

    override val container =
        container<AllCardContract.UIState, AllCardContract.SideEffect>(AllCardContract.UIState.InitState)

}