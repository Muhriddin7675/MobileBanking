package com.example.mobilebanking.ui.componnent.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.homeCardColor
import com.example.mobilebanking.ui.theme.textButtonGreenColor
import com.example.mobilebanking.ui.theme.white


@Composable
fun CardMyHouse() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
            .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(homeCardColor)

    ) {
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                    text = stringResource(id = R.string.paymentSecurity),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                )
                Text(
                    text = stringResource(id = R.string.electric_gas_connection),
                    modifier = Modifier.padding(start = 12.dp),
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
                )
                Text(
                    modifier = Modifier
                        .padding(12.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(textButtonGreenColor)
                        .padding(start = 6.dp, end = 6.dp, bottom = 4.dp, top = 4.dp)
                    ,
                    text = stringResource(id = R.string.create_home),
                    color = white,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)

                    )
                )

            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(R.drawable.identification_step_one),
                    contentDescription = "security",
                    modifier = Modifier.align(Alignment.Center)
                )

            }
        }
    }
}

@Preview
@Composable
fun CardMyHousePreview() {
    MobileBankingTheme {
        CardMyHouse()
    }
}