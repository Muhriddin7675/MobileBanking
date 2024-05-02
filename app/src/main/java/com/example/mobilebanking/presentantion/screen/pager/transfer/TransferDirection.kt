package com.example.mobilebanking.presentantion.screen.pager.transfer

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface TransferDirection {
}
class  TransferDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
):TransferDirection{

}
