package com.example.mobilebanking.ui.componnent.card

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.iconColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CardIconTwoText(
    onClick: () -> Unit,
    textTitle: String,
    text: String,
    icon: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource(),
                enabled = true,
                onClickLabel = "",
                onClick = {
                    onClick.invoke()
                })
            .clip(RoundedCornerShape(16.dp))
            .shadow(1.dp, RoundedCornerShape(16.dp))
            .background(white)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "support",
            modifier = Modifier
                .padding(start = 16.dp, end = 12.dp)
                .align(Alignment.CenterVertically)
                .size(28.dp),
            colorFilter = ColorFilter.tint(iconColor)
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = 16.dp)
                .padding(end = 16.dp)
        ) {
            Text(
                text = textTitle,
                modifier = Modifier,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_regular))
            )
            Text(
                text = text,
                fontSize = 14.sp,
                color = textColor,
                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
            )
        }
    }
}

@Preview
@Composable
fun CardIconTwoTextPreview() {
    MobileBankingTheme {
        CardIconTwoText(
            onClick = { /*TODO*/ },
            textTitle = stringResource(id = R.string.verify_your_identity),
            text = stringResource(
                id = R.string.spending_more_money_on_paynet_card
            ),
            icon = R.drawable.ic_refresh
        )
    }
}