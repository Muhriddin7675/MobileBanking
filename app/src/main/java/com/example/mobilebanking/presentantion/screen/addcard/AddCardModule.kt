package com.example.mobilebanking.presentantion.screen.addcard

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddCardModule @Inject constructor(
    private val direction: AddCardDirection,
    private val repository: AppRepository,
) :ViewModel(),AddCardContract.Model{
    override fun onEventDispatcher(intent: AddCardContract.Intent) = intent {
        when(intent){
            is AddCardContract.Intent.AddCard ->{

            }
            AddCardContract.Intent.PopBackStack ->{
                direction.popBackStack()
            }
        }
    }

    override val container = container<AddCardContract.UIState, AddCardContract.SideEffect>(AddCardContract.UIState.InitState)
}