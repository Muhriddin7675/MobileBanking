package com.example.mobilebanking.presentantion.screen.profil

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.data.local.pref.MyShared
import com.example.mobilebanking.domain.repositori.AppRepository
import com.example.mobilebanking.domain.repositori.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfilModel @Inject constructor(
    private val repository: AppRepository,
    private val direction: ProfilDirection,
    private val repo: LocalRepository,
    private val myShared: MyShared
) : ViewModel(), ProfilContract.Model {
    override fun onEventDispatcher(intent: ProfilContract.Intent) = intent {
        when (intent) {
            ProfilContract.Intent.OpenMapScreen -> {
                direction.openMapScreen()
            }

            ProfilContract.Intent.PopBackStack -> {
                direction.popBackStack()
            }

            ProfilContract.Intent.LogOut -> {
                repo.deleteAllItems()
                myShared.setPassword("password")
                direction.logOut()
            }

            ProfilContract.Intent.OpenPaymentAbout -> {
                postSideEffect(ProfilContract.SideEffect.OpenPaymentAbout)
            }

            ProfilContract.Intent.OpenReference -> {
                postSideEffect(ProfilContract.SideEffect.OpenReference)

            }

            ProfilContract.Intent.OpenRite -> {
                postSideEffect(ProfilContract.SideEffect.OpenRite)

            }

            ProfilContract.Intent.OpenSettingScreen -> {
                direction.openSettingScreen()
            }

            ProfilContract.Intent.OpenLogAut -> {
                postSideEffect(ProfilContract.SideEffect.OpenLogAut)

            }

            ProfilContract.Intent.InitUiState -> {
                reduce { ProfilContract.UIState.InitState("+998${myShared.getPassword()}") }
            }

            ProfilContract.Intent.OpenIdentityVerificationScreen -> {
                direction.openIdentityVerificationScreen()
            }
        }
    }

    override val container =
        container<ProfilContract.UIState, ProfilContract.SideEffect>(
            ProfilContract.UIState.InitState("+998 90 909 00 00")
        )

}