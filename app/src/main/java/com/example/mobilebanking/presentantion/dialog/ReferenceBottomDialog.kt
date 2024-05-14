package com.example.mobilebanking.presentantion.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.R
import com.example.mobilebanking.presentantion.screen.profil.CartTextComponent
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.gray
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.openWebsite

class ReferenceBottomDialog(val clickCancel: () -> Unit) : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            ReferenceBottomDialogContent(clickCancel = {
                clickCancel.invoke()

            }
            )
        }
    }
}

@Composable
fun ReferenceBottomDialogContent(
    clickCancel: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
            .background(white)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 6.dp)
                .height(4.5.dp)
                .width(36.dp)
                .clip(CircleShape)
                .background(gray)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(id = R.string.reference),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            modifier = Modifier.padding(start = 16.dp, top = 12.dp)
        )
        CartTextComponent(
            icon = R.drawable.ic_document,
            text = stringResource(id = R.string.publicOffering),
            onClick = {
                clickCancel.invoke()
                openWebsite(context,"https://uploads-ssl.webflow.com/63a7038e6eb0c1f38cd4d11f/64b8ecef9bd4e117c602cbc9_оферта%20кошелька%20(2).pdf")
            })
        CartTextComponent(
            icon = R.drawable.ic_document,
            text = stringResource(id = R.string.paymentBonusCard),
            onClick = {
                clickCancel.invoke()
                openWebsite(context,"https://assets-global.website-files.com/63a7038e6eb0c1f38cd4d11f/6602de34fdf497829debb1df_оферта%20финал%20final.pdf")

            })

        CartTextComponent(
            icon = R.drawable.ic_document,
            text = stringResource(id = R.string.paymentElectronMoneyOffering),
            onClick = {
                clickCancel.invoke()
                openWebsite(context,"https://uploads-ssl.webflow.com/63a7038e6eb0c1f38cd4d11f/64b8ecef9bd4e117c602cbc9_оферта%20кошелька%20(2).pdf")

            })

        Spacer(modifier = Modifier.height(24.dp))
    }

}

@Preview
@Composable
fun ReferenceBottomDialogPreview() {
    MobileBankingTheme {
        ReferenceBottomDialogContent {
        }
    }
}
