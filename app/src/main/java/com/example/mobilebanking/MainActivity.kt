package com.example.mobilebanking

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import com.example.mobilebanking.presentantion.screen.splash.SplashScreen
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.util.NetworkStatusValidator
import com.example.mobilebanking.util.changeColorStatusBar
import com.example.mobilebanking.util.navigation.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @Inject
    lateinit var networkStatusValidator: NetworkStatusValidator

    @Inject
    lateinit var handler: NavigationHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeColorStatusBar(color = R.color.bg_app, true)
        networkStatusValidator.init(
            availableNetworkBlock = {},
            lostConnection = {}
        )
        setContent {
            MobileBankingTheme {
                // A surface container using the 'background' color from the theme
                BottomSheetNavigator(
                    sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ) {
                    Navigator(SplashScreen()) { navigator ->
                        LaunchedEffect(key1 = navigator) {
                            handler.navigationStack
                                .onEach { it(navigator) }
                                .launchIn(lifecycleScope)
                        }
                        CurrentScreen()
                    }
                }
            }
        }
    }
}
