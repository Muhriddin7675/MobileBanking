package com.example.mobilebanking.presentantion.screen.dataentry

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface DataEntryDirection {
    suspend fun popBackStack()
}

@Singleton
class DataEntryDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : DataEntryDirection {
    override suspend fun popBackStack() {
        navigator.back()
    }

}