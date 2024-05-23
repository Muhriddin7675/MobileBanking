package com.example.mobilebanking.presentantion.screen.identity

import com.example.mobilebanking.presentantion.screen.dataentry.DataEntryScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface IdentityVerificationDirection {
    suspend fun popBackStack()
    suspend fun openDataEntryScreen()
}

@Singleton
class IdentityVerificationDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : IdentityVerificationDirection {
    override suspend fun popBackStack() {
        navigator.back()
    }

    override suspend fun openDataEntryScreen() {
        navigator.navigateTo(DataEntryScreen())
    }

}