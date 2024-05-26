package com.example.mobilebanking.ui.componnent.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.mobilebanking.ui.theme.callCardColor

@Composable
fun CardTopTextBottomImage(
    @DrawableRes icon: Int,
    textTitle: String,
    modifier: Modifier,
    boolImageStartInEnd: Boolean = true
) {
    Column(
       modifier = modifier
    ) {
        Text(
            text = textTitle,
            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 20.dp),
            fontSize = 18.sp,
            color = black,
            fontFamily = FontFamily(Font(R.font.pnfont_regular))
        )
        Image(
            painter = painterResource(id = icon), contentDescription = null,
            modifier = if (boolImageStartInEnd) {
                Modifier.align(Alignment.Start)
                    .fillMaxHeight()
            } else {
                Modifier.align(Alignment.End)
                       .fillMaxHeight()

            }
        )
    }

}

@Preview
@Composable
fun PreviewCard() {
    MobileBankingTheme {
        CardTopTextBottomImage(
            modifier =  Modifier
                .fillMaxWidth()
                .padding(end = 6.dp)
                .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(callCardColor)
                .height(180.dp)
                .clickable {},
            icon = R.drawable.communication,
            textTitle = stringResource(R.string.call))
    }

}