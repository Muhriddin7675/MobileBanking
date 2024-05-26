package com.example.mobilebanking.ui.componnent.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.black


@Composable
fun CardStartTextEndImage(
    modifier: Modifier,
    @StringRes text: Int,
    @DrawableRes image: Int,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = text),
            Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(start = 12.dp),
            fontSize = 16.sp,
            color = black,
            fontFamily = FontFamily(Font(R.font.pnfont_regular))

        )
        Image(
            painter = painterResource(id = image), contentDescription = "",
            modifier = Modifier
                .weight(0.8f)
                .fillMaxHeight()
        )
    }

}

