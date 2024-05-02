package com.example.mobilebanking.presentantion.screen.main

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject


interface MainDirection {

}
class MainDirectionImpl @Inject constructor(
    private val navigation: AppNavigator
) : MainDirection {


}
