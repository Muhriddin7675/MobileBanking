package com.example.mobilebanking.presentantion.screen.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SplashViewModel>()
        viewModel.onEventDispatcher(SplashIntent.Intro)
        SplashContent()
    }

}

@Composable
private fun SplashContent() {
   Column(modifier = Modifier
       .fillMaxSize()){
       AnimatedPreloader()
   }
}

@Preview
@Composable
private fun SplashPreview() {
    MobileBankingTheme {
        SplashContent()
    }
}

@Composable
fun AnimatedPreloader() {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
private fun PreviewSplashContent() {
    MobileBankingTheme {
        SplashContent()
    }
}