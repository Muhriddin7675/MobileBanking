package com.example.mobilebanking.presentantion.screen.main

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.mobilebanking.presentantion.screen.pager.history.HistoryPage
import com.example.mobilebanking.presentantion.screen.pager.home.HomePage
import com.example.mobilebanking.presentantion.screen.pager.payment.PaymentPage
import com.example.mobilebanking.presentantion.screen.pager.transfer.TransfersPage
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.util.TabNavigatorItem
import org.orbitmvi.orbit.compose.collectAsState


class MainScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel: MainContract.Model = getViewModel<MainModel>()

//        "MainScreen -> $this".myLog()
//        "MainScreen screenModel -> $screenModel".myLog()

        MobileBankingTheme {
            val uiState = screenModel.collectAsState().value
            MainContent(uiState = uiState, onEventDispatcher = screenModel::onEventDispatcher)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainContent(
    uiState: MainContract.UIState,
    onEventDispatcher: (MainContract.Intent) -> Unit
) {
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
                    contentColor = Color.Black,
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

/*class MainScreen : Screen {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    override fun Content() {
        TabNavigator(HomePage) {
            Scaffold(
                content = {
                    CurrentTab()
                },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomePage)
                        TabNavigationItem(TransfersPage)
                        TabNavigationItem(PaymentPage)
                        TabNavigationItem(HistoryPage)
                    }
                }
            )
        }
    }

}
@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val interactionSource = remember { MutableInteractionSource() }

    BottomNavigationItem(
        modifier = Modifier
            .background(color = Color.White),
        selectedContentColor = selectItemColor,
        unselectedContentColor = unSelectedItemColor,
        label = { Text(text = tab.options.title)},
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = tab.options.title) } },
        interactionSource = interactionSource,
        alwaysShowLabel = true
    )
}*/

@Composable
@Preview(showBackground = true)
private fun MainContentPreview() {
    MobileBankingTheme {
        MainContent(uiState = MainContract.UIState.InitState) {}
    }
}

