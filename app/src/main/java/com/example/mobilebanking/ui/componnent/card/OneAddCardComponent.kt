package com.example.mobilebanking.ui.componnent.card

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.buttonInvisibleColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.addSpacesEveryAmount
import com.example.mobilebanking.util.getGradient

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun OneAddCardComponent(
    cardData: CardData,
    showBalance:Boolean,
    addCardClick: () -> Unit,
    cardClick: (CardData) -> Unit
) {

    val cardName by remember { mutableStateOf(cardData.name) }
    val cardSumma by remember { mutableStateOf(cardData.amount.toString().addSpacesEveryAmount()) }
    val selectedColor by remember { mutableStateOf(getGradient(cardData.themeType)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(appBackgroundColorWhite)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = true,
                    onClickLabel = null,
                    role = null,
                    onClick = {
                        addCardClick.invoke()
                    })
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.add_card),
                Modifier.align(Alignment.Center),
                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                color = black
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .shadow(1.dp, RoundedCornerShape(16.dp))
                .background(
                    brush = selectedColor, shape = RoundedCornerShape(16.dp)
                ).clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = true,
                    onClickLabel = null,
                    role = null,
                    onClick = {
                        cardClick.invoke(cardData)
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
                            .fillMaxHeight()
                            .height(20.dp)
                            .width(60.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = if (showBalance) cardSumma else "• •••",
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
                    text = cardName,
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
fun OneAddCardComponentPreview() {
    MobileBankingTheme {
        OneAddCardComponent(
            cardData = CardData(
                id = 0,
                name = "HUMO",
                amount = 1000000,
                owner = "",
                pan = "0099",
                expiredMonth = 0
            ),showBalance = true, addCardClick = {}, cardClick = {}
        )
    }
}