package com.example.mobilebanking.ui.componnent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.trainTicketCardColor
import com.example.mobilebanking.ui.theme.white


@Composable
fun TrainTicked(
    modifier: Modifier,
    onClickButton: () -> Unit,
    titleText: String,
    text: String,
    buttonText: String,
    icon: Int,
    color: Color,
) {

    Column(
        modifier = modifier
    )
    {
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                    text = titleText,
                    color = black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                )

                Text(
                    text = text,
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
                        .shadow(1.dp, CircleShape)
                        .height (32.dp),
                onClick = { onClickButton.invoke() },
                colors = ButtonDefaults.buttonColors(white),

                ) {
                Text(
                    text = buttonText,
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                )
            }
            }
            Box(
                modifier = Modifier
                    .weight(0.8f)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = "security",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(end = 8.dp)
                )

            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    MobileBankingTheme {
        TrainTicked(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(trainTicketCardColor),
            onClickButton = { },
            titleText = stringResource(id = R.string.train_ticket),
            text = stringResource(id = R.string.comfort_quick),
            buttonText = stringResource(id = R.string.buy),
            icon = R.drawable.paynet_railway,
            color = trainTicketCardColor
        )
    }

}
