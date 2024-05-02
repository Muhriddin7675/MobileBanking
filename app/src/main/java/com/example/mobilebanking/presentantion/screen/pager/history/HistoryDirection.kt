package com.example.mobilebanking.presentantion.screen.pager.history

import com.example.mobilebanking.util.navigation.AppNavigator
import javax.inject.Inject

interface HistoryDirection {

}
class HistoryDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : HistoryDirection {


}

