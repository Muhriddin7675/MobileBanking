package com.example.mobilebanking.ui.componnent

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.buttonInvisibleColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.addSpacesEveryAmount
import com.example.mobilebanking.util.getGradient

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun TwoAddCardComponent(
    cardClick :(CardData)->Unit,
    cardList:List<CardData>,
    showBalance:Boolean
) {

    val selectedColor1 by remember { mutableStateOf(getGradient(cardList[0].themeType)) }
    val selectedColor2 by remember { mutableStateOf(getGradient(cardList[1].themeType)) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .padding(vertical = 4.dp)
            .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(appBackgroundColorWhite)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .shadow(1.dp, RoundedCornerShape(16.dp))
                .background(
                    brush = selectedColor1, shape = RoundedCornerShape(16.dp)
                )
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = true,
                    onClickLabel = null,
                    role = null,
                    onClick = {
                        cardClick.invoke(cardList[0])
                    })
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {

                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .height(42.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_paymentsystem_humo),
                        contentDescription = "",
                        modifier = Modifier
                            .height(30.dp)
                            .width(60.dp)
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = if (showBalance) cardList[0].amount.toString().addSpacesEveryAmount() else "• •••",

                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 14.sp,
                    color = white
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.som),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 14.sp,
                    color = buttonInvisibleColor
                )
            }
            Row {
                Text(
                    text = cardList[0].name,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = white,
                    modifier = Modifier.padding(start = 12.dp, top = 2.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .align(Alignment.BottomCenter)
                .shadow(1.dp, RoundedCornerShape(16.dp))
                .background(
                    brush = selectedColor2, shape = RoundedCornerShape(16.dp)
                )
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = true,
                    onClickLabel = null,
                    role = null,
                    onClick = {
                        cardClick.invoke(cardList[1])
                    })
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)

            ) {

                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .height(42.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_paymentsystem_humo),
                        contentDescription = "",
                        modifier = Modifier
                            .height(30.dp)
                            .width(60.dp)
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = if (showBalance) cardList[1].amount.toString().addSpacesEveryAmount() else "• •••",
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 14.sp,
                    color = white
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.som),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 14.sp,
                    color = buttonInvisibleColor
                )
            }
            Row {
                Text(
                    text = cardList[1].name,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = white,
                    modifier = Modifier.padding(start = 12.dp, top = 2.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }

        }
    }
}

@Preview
@Composable
fun TwoAddCardComponentPreview() {
    MobileBankingTheme {
//        TwoAddCardComponent(cardList = )
    }
}