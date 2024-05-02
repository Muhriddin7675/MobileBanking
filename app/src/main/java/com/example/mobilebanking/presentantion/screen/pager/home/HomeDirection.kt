package com.example.mobilebanking.presentantion.screen.pager.home

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface HomeDirection {
}
class HomeDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
):HomeDirection{


}