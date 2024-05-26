package com.example.mobilebanking.presentantion.screen.pincheck

import com.example.mobilebanking.presentantion.screen.pager.MainScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface PinDirection {
    suspend fun toBottomNavigation()
}

@Singleton
class PinDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : PinDirection {
    override suspend fun toBottomNavigation() {
        appNavigator.replace(MainScreen())
    }
}