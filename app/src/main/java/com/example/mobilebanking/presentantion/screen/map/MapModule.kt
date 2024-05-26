package com.example.mobilebanking.presentantion.screen.map

import androidx.lifecycle.ViewModel
import com.example.mobilebanking.domain.repositori.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class MapModule @Inject constructor(
    private val direction: MapDirection,
    private val repository: AppRepository
) : MapContract.Model, ViewModel() {
    override fun onEventDispatcher(intent: MapContract.Intent)  = intent{
        when (intent) {
            is MapContract.Intent.OpenMapDialog -> {
                postSideEffect(MapContract.SideEffect.OpenMapBottomDialog(intent.markerData))
            }
        }
    }

    override val container =
        container<MapContract.UIState, MapContract.SideEffect>(MapContract.UIState.InitState)
}