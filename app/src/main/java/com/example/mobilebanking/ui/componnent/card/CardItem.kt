package com.example.mobilebanking.ui.componnent.card

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilebank.ui.components.CustomImageView
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.ui.componnent.text.CustomTextView
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.util.addSpacesEveryAmount
import com.example.mobilebanking.util.getCardType
import com.example.mobilebanking.util.getGradient

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CardItemAll(
    data: CardData,
    cardClick: () -> Unit
) {
    val selectedColor by remember { mutableStateOf(getGradient(data.themeType)) }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .height(220.dp)
            .background(
                brush = selectedColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                enabled = true,
                onClickLabel = null,
                role = null,
                onClick = {
                    cardClick.invoke()
                })
            .fillMaxWidth()
    ) {

        if (getCardType(data.pan) == R.drawable.ic_paymentsystem_humo) {
            CustomImageView(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(160.dp)
                    .height(50.dp),
                src = getCardType(data.pan)
            )
        } else {
            CustomImageView(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 24.dp)
                    .size(90.dp),
                src = getCardType(data.pan)
            )
        }

        CustomTextView(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 16.dp, start = 16.dp),
            text = data.name,
            color = Color.White,
            fontSize = 20
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .fillMaxWidth()
        ) {
            CustomTextView(
                text = data.amount.toString().addSpacesEveryAmount(),
                fontSize = 28,
                color = Color.White,
                fontWeight = 700
            )
            CustomTextView(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = stringResource(id = R.string.som),
                fontSize = 24,
                color = Color(0x9CFFFFFF),
                fontWeight = 600
            )
        }

        CustomTextView(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 56.dp, start = 16.dp),
            text = "**** **** **** ${data.pan}",
            color = Color.White,
            fontSize = 16,
            fontWeight = 20
        )

        CustomTextView(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(top = 56.dp, end = 16.dp),
            text = if (data.expiredMonth < 10) "0${data.expiredMonth}/${
                data.expiredYear.toString().substring(2)
            }" else "${data.expiredMonth}/${data.expiredYear.toString().substring(2)}",
            color = Color.White,
            fontSize = 16,
            fontWeight = 20
        )

    }
}

@Preview
@Composable
fun CardItemAllPrev() {
    MobileBankingTheme {
        CardItemAll(
            CardData(
                id = 0,
                name = "HUMO",
                amount = 1000000,
                pan = "1122",
                owner = "",
                expiredYear = "1020",
                expiredMonth = 10,
                themeType = 1,
                isVisible = false
            ),{}
        )
    }
}