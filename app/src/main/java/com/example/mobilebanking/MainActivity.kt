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
import com.example.mobilebanking.data.remote.ConnectivityLiveData
import com.example.mobilebanking.domain.uscase.GetAllCardUseCase
import com.example.mobilebanking.presentantion.screen.network.NoConnectionScreen
import com.example.mobilebanking.presentantion.screen.splash.SplashScreen
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.util.MyDataLoader
import com.example.mobilebanking.util.NetworkStatusValidator
import com.example.mobilebanking.util.changeColorStatusBar
import com.example.mobilebanking.util.navigation.AppNavigator
import com.example.mobilebanking.util.navigation.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @Inject
    lateinit var networkStatusValidator: NetworkStatusValidator
    @Inject
    lateinit var useCase: GetAllCardUseCase
    @Inject
    lateinit var handler: NavigationHandler

    @Inject
    lateinit var dispatcher: AppNavigator

    private var time = 0L
    object NetworkStatus {
        val hasNetwork = MutableStateFlow(true)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyDataLoader.init(useCase)


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
                    NetworkStatus.hasNetwork.onEach {
                        if (!it) dispatcher.navigateTo(NoConnectionScreen())
                    }.launchIn(lifecycleScope)
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
//        setLocale(this, pref.appLanguage())
        val connectivityLiveData = ConnectivityLiveData(this)

        connectivityLiveData.observe(this) { networkState ->
            if (networkState.isConnected) {
                NetworkStatus.hasNetwork.tryEmit(true)

            } else {
                NetworkStatus.hasNetwork.tryEmit(false)
            }
        }

    }

    override fun onPause() {
        super.onPause()
//        pref.changeLanguage(getCurrentLanguage())
        time = System.currentTimeMillis()
    }
}
