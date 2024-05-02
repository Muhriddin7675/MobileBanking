package com.example.mobilebanking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.mobilebanking.presentantion.screen.splash.SplashScreen
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.util.navigation.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var handler: NavigationHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileBankingTheme {
                // A surface container using the 'background' color from the theme
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
