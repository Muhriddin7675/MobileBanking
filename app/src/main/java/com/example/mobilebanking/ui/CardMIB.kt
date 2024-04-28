package com.example.mobilebanking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.mobilebanking.ui.theme.aviaCardColor
import com.example.mobilebanking.ui.theme.white

@Composable
fun CardMIB() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(aviaCardColor)
    )
    {
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                    text = stringResource(id = R.string.qarzdorlikni_bilib),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                )

                Text(
                    text = stringResource(id = R.string.ilovada_soliq_va),
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            bottom = 12.dp,
                            top = 8.dp
                        )
                        .height(32.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(white),

                    ) {
                    Text(
                        text = stringResource(id = R.string.shaxsni_tasdiqlang),
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular))
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(R.drawable.my_debts),
                    contentDescription = "security",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .height(120.dp)
                )

            }
        }
    }
}

@Preview
@Composable
fun FunctionPreview() {
    MobileBankingTheme {
        CardMIB()
    }

}