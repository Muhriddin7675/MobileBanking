package com.example.mobilebanking.presentantion.screen.map

import com.example.mobilebanking.data.model.MarkerData
import org.orbitmvi.orbit.ContainerHost

interface MapContract {


    interface Model : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState

    }

    sealed interface SideEffect {
        data class OpenMapBottomDialog(val data: MarkerData) : SideEffect

    }

    sealed interface Intent {
        data class OpenMapDialog (val markerData: MarkerData):Intent

    }
}