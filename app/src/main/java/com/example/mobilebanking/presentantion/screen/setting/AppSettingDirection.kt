package com.example.mobilebanking.presentantion.screen.setting

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface AppSettingDirection {
suspend fun PopBackStack()
}

class AppSettingDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : AppSettingDirection{
    override suspend fun PopBackStack() {
        navigator.back()
    }

}