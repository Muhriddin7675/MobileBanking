package com.example.mobilebanking.presentantion.screen.profil

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebanking.ui.theme.MobileBankingTheme

class ProfilScreen : Screen {
    @Composable
    override fun Content() {
        val model: ProfilContract.Model = getViewModel<ProfilModel>()


    }

    @Composable
    fun ProfilContent() {

    }

    @Preview
    @Composable
    fun ProfilPreview() {
        MobileBankingTheme {
            ProfilContent()
        }

    }

}
