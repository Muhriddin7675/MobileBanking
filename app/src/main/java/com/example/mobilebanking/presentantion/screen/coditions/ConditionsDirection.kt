package com.example.mobilebanking.presentantion.screen.coditions

import com.example.mobilebanking.presentantion.screen.identity.IdentityVerificationScreen
import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ConditionsDirection {
    suspend fun popBackStack()
    suspend fun openIdentityScreen()
}

@Singleton
class ConditionsDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : ConditionsDirection {
    override suspend fun popBackStack() {
        navigator.back()
    }

    override suspend fun openIdentityScreen() {
        navigator.navigateTo(IdentityVerificationScreen())
    }

}