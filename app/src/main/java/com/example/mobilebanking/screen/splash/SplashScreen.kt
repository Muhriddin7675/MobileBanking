package com.example.mobilebanking.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme

class SplashScreen: Screen {
    @Composable
    override fun Content() {

    }
    @Composable
    fun SplashContent(){
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Row(modifier = Modifier.align(Alignment.Center)) {
                Image(painter = painterResource(id = R.drawable.image_crickle_green),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                        .align(Alignment.Bottom))

                Text(text = "paynet",
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold )),
                    fontSize = 64.sp)
            }
        }
    }
    @Preview
    @Composable
    fun PreviewSpleshContent() {
        MobileBankingTheme {
            SplashContent()
        }

    }

}