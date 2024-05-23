package com.example.mobilebanking.presentantion.screen.addcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilebanking.domain.AppRepository
import com.example.mobilebanking.domain.uscase.AddCardUseCase
import com.example.mobilebanking.util.myLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddCardModule @Inject constructor(
    private val direction: AddCardDirection,
    private val repository: AppRepository,
    private val addCardUseCase: AddCardUseCase
) : ViewModel(), AddCardContract.Model {
    override fun onEventDispatcher(intent: AddCardContract.Intent) = intent {
        when (intent) {
            is AddCardContract.Intent.AddCard -> {

                myLog("cardYear ${intent.expiryYear} cardMonth ${intent.expiryMonth} cardNumber ${intent.cardNumber}")
                addCardUseCase.invoke(
                    pan = intent.cardNumber,
                    expiredYear = intent.expiryYear,
                    expiredMonth = intent.expiryMonth
                ).onEach {
                    it.onFailure {}
                    it.onSuccess { direction.popBackStack() }
                }.launchIn(viewModelScope)
            }

            AddCardContract.Intent.PopBackStack -> {
                direction.popBackStack()
            }
        }
    }

    override val container =
        container<AddCardContract.UIState, AddCardContract.SideEffect>(AddCardContract.UIState.InitState)
}