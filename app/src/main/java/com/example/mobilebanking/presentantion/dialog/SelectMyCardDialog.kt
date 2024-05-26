package com.example.mobilebanking.presentantion.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebank.ui.components.CustomImageView
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.ui.componnent.text.CustomTextView
import com.example.mobilebanking.util.MyDataLoader
import com.example.mobilebanking.util.addSpacesEveryAmount
import com.example.mobilebanking.util.getCardType
import com.example.mobilebanking.util.getGradient
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SelectMyCardDialog(
    private val addClick: () -> Unit,
    private val itemClick: (CardData) -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        var list by remember {
            mutableStateOf(listOf<CardData>())
        }
        MyDataLoader.listStateFlow.onEach  {
            list = it
        }.launchIn(scope)
        SelectMyCardContent(
            ls = list,
            addClick = addClick,
            itemClick = itemClick
        )
    }
}

@Composable
private fun SelectMyCardContent(
    ls: List<CardData>,
    addClick: () -> Unit,
    itemClick: (CardData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
                .width(30.dp)
                .height(4.dp)
                .background(Color(0xFFADADAD), shape = RoundedCornerShape(4.dp))
        )
        CustomTextView(
            modifier = Modifier
                .padding(start = 16.dp, top = 12.dp),
            text = stringResource(id = R.string.cards),
            fontSize = 17
        )

        LazyColumn {
            items(ls.size) {
                CardItem(
                    data = ls[it],
                    onClick = itemClick
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp, top = 24.dp)
                .height(48.dp)
                .fillMaxWidth(),
            border = BorderStroke(width = 1.dp, brush = Brush.horizontalGradient(colors = listOf(
                Color.Black, Color.Black))),
            colors = ButtonDefaults.buttonColors(Color.White),
            elevation = ButtonDefaults.buttonElevation(1.dp),
            onClick = addClick
        ) {
            CustomImageView(
                modifier = Modifier
                    .rotate(45F)
                    .size(24.dp),
                src = R.drawable.ic_plus_card
            )

            CustomTextView(
                modifier = Modifier
                    .padding(start = 12.dp),
                text = stringResource(id = R.string.add_card),
                color = Color.Black,
                fontSize = 18,
                fontWeight = 600
            )
        }

    }
}

@Composable
private fun CardItem(modifier: Modifier = Modifier, data: CardData, onClick: (CardData) -> Unit) {
    val cardType = getCardType(data.pan)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(72.dp)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) { onClick(data) }
    ) {
        Box(
            modifier = Modifier
                .padding(start = 12.dp)
                .width(84.dp)
                .height(56.dp)
                .align(Alignment.CenterStart)
                .background(brush = getGradient(0), shape = RoundedCornerShape(8.dp))
        ) {
            CustomImageView(
                modifier = Modifier
                    .padding(
                        end = 8.dp,
                        bottom = if (cardType == R.drawable.ic_paymentsystem_uzcard) 4.dp else 8.dp
                    )
                    .width(40.dp)
                    .align(Alignment.BottomEnd),
                src = cardType
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 112.dp)
                .align(Alignment.Center)
                .fillMaxWidth(),
        ) {
            CustomTextView(
                text = (if (cardType == R.drawable.ic_paymentsystem_uzcard) "Uzcard" else "Humo") + " • ${data.pan}",
                fontSize = 18,
                fontWeight = 600,
                color = Color.Black
            )

            CustomTextView(
                text = data.amount.toString().addSpacesEveryAmount() + " " + stringResource(id = R.string.som),
                fontSize = 14,
                color = Color(0xFF6B6B6B)
            )
        }
    }
}


