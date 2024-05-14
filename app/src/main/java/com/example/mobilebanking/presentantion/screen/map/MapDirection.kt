package com.example.mobilebanking.presentantion.screen.map

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface MapDirection {

}
class MapDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
):MapDirection{

}