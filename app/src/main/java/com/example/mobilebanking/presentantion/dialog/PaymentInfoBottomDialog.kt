package com.example.mobilebanking.presentantion.dialog

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.gray
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.textLinkColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.openWebsite

class PaymentInfoBottomDialog(
    val clickCancel: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        MobileBankingTheme {
            PaymentInfoContent(clickCancel = {
                clickCancel.invoke()
                openWebsite(context,"https://www.paynet.uz")

            }
            )
        }
    }

}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun PaymentInfoContent(
    clickCancel: () -> Unit
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
        Image(
            painter = painterResource(id = R.drawable.paynet_info_logo), contentDescription = "",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = stringResource(id = R.string.paynetInfo),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
            color = textColor
        )
        Text(
            text = stringResource(id = R.string.paynetLink),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = true,
                    onClickLabel = "",
                    role = null,
                    onClick = { clickCancel.invoke() }),
            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
            color = textLinkColor
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun PaymentInfoPreview() {
    MobileBankingTheme {
        PaymentInfoContent {}
    }
}
