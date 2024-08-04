package com.example.mobilebanking.presentantion.screen.pager

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.mobilebanking.presentantion.screen.pager.history.HistoryPage
import com.example.mobilebanking.presentantion.screen.pager.home.HomePage
import com.example.mobilebanking.presentantion.screen.pager.payment.PaymentPage
import com.example.mobilebanking.presentantion.screen.pager.transfer.TransfersPage
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.util.TabNavigatorItem

class MainScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme { MainContent() }
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainContent() {
    TabNavigator(
        tab = HomePage,
        tabDisposable = {
            TabDisposable(
                navigator = it,
                tabs = listOf(HomePage, TransfersPage, PaymentPage, HistoryPage)
            )
        }
    ) {
        Scaffold(
            content = {
                CurrentTab()
            },
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color.White,
                    contentColor = textColor,
                ) {
                    TabNavigatorItem(tab = HomePage)
                    TabNavigatorItem(tab = TransfersPage)
                    TabNavigatorItem(tab = PaymentPage)
                    TabNavigatorItem(tab = HistoryPage)
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun MainContentPreview() {
    MobileBankingTheme {
        MainContent()
    }
}

