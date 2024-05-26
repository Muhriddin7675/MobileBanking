package com.example.mobilebanking.ui.componnent.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.requisiteCardColor

@Composable
fun CardTextEndIcon(
    icon: Int,
    text: String,
    clickItem: () -> Unit
) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(16.dp))
            .height(72.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(requisiteCardColor)
            .clickable { clickItem.invoke() }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
                .padding(end = 56.dp),
            fontSize = 18.sp,
            color = black,
            fontFamily = FontFamily(Font(R.font.pnfont_regular))
        )
        Image(
            painter = painterResource(id = icon), contentDescription = "",
            modifier = Modifier
                .padding(end = 12.dp)
                .size(36.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Preview
@Composable
fun CardPreview() {
    MobileBankingTheme {
        CardTextEndIcon(
            icon = R.drawable.ic_operations_timecircle,
            text = stringResource(id = R.string.request),
            {}
        )
    }
}