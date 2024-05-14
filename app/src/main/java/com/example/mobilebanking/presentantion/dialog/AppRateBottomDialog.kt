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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.btnInvisibleColor
import com.example.mobilebanking.ui.theme.gray
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white

class AppRateBottomDialog(
    val clickGood: () -> Unit,
    val clickBad: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        MobileBankingTheme {
            AppRateDialogContent(
                clickGood = {
                    clickGood.invoke()
                },
                clickBad = {
                    clickBad.invoke()
                }
            )
        }
    }
}

@Composable
fun AppRateDialogContent(
    clickGood: () -> Unit,
    clickBad: () -> Unit
) {
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
            text = stringResource(id = R.string.paymentYouLove),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            modifier = Modifier.padding(start = 16.dp, top = 12.dp)
        )

        Text(
            text = stringResource(id = R.string.paymentYouLoveAbout),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
            modifier = Modifier.padding(start = 16.dp, top = 12.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                disabledContainerColor = btnInvisibleColor,
                disabledContentColor = white
            ),
            onClick = {
                clickGood.invoke()

            }) {
            Text(
                text = stringResource(id = R.string.btn_everything_is_fine),
                fontFamily = FontFamily(Font(R.font.pnfont_medium))
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(
            onClick = {
                clickBad.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btn_something),
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                color = textColor
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }

}

@Preview
@Composable
fun AppRatePreview() {
    MobileBankingTheme {
        AppRateDialogContent({}, {})
    }

}