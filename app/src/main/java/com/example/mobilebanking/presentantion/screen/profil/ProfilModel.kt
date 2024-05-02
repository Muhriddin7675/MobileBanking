package com.example.mobilebanking.presentantion.screen.profil

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfilModel @Inject constructor(
    private val repository: AppRepository,
    private val direction: ProfilDirection
) : ViewModel(), ProfilContract.Model {
    override fun onEventDispatcher(intent: ProfilContract.Intent) {
        TODO("Not yet implemented")
    }

    override val container =
        container<ProfilContract.UIState, ProfilContract.SideEffect>(ProfilContract.UIState.InitState)

}