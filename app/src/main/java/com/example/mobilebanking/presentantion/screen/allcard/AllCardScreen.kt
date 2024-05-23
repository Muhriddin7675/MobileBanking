package com.example.mobilebanking.presentantion.screen.allcard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.presentantion.dialog.AddCardBottomDialog
import com.example.mobilebanking.ui.componnent.card.CardItemAll
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.btnInvisibleColor
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.white
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class AllCardScreen : Screen {
    @Composable
    override fun Content() {
        val model: AllCardContract.Model = getViewModel<AllCardModel>()
        MobileBankingTheme {
            val uiState = model.collectAsState()
            AllCardContent(uiState = uiState.value, onEventDispatcher = model::onEventDispatcher)
        }
        val bottomNavigator = LocalBottomSheetNavigator.current
        model.collectSideEffect { sideEffect ->
            when (sideEffect) {
                AllCardContract.SideEffect.OpenAddCardBottomDialog -> {
                    bottomNavigator.show(AddCardBottomDialog(
                        clickUzCard = {
                            bottomNavigator.hide()
                            model.onEventDispatcher(AllCardContract.Intent.OpenAddCardScreen)
                        }, clickRuCard = {
                            bottomNavigator.hide()
                        })
                    )
                }
            }
        }
    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun AllCardContent(
        uiState: AllCardContract.UIState, onEventDispatcher: (AllCardContract.Intent) -> Unit

    ) {
        LaunchedEffect(Unit) {
            onEventDispatcher.invoke(AllCardContract.Intent.SetCardList)
        }

        var cardList = emptyList<CardData>()
        when (uiState) {
            is AllCardContract.UIState.SetCardList -> {
                cardList = uiState.cardList
            }

            else -> {}
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(appBackgroundColorWhite)
        ) {


            Row(
                Modifier
                    .padding(horizontal = 16.dp)
                    .height(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_navigation_arrow_left_x24),
                    contentDescription = "back",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable(interactionSource = MutableInteractionSource(),
                            indication = null,
                            enabled = true,
                            onClickLabel = null,
                            onClick = {
                                onEventDispatcher.invoke(AllCardContract.Intent.PopBackStack)
                            }),
                )


                Text(
                    text = stringResource(id = R.string.my_cards),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    color = black,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 12.dp)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(cardList.size) {
                    CardItemAll(data = cardList[it]) {
                        onEventDispatcher.invoke(
                            AllCardContract.Intent.OpenCardDetailScreen(
                                cardList[it]
                            )
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                    .background(white)
            ) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor,
                        contentColor = white,
                        disabledContainerColor = btnInvisibleColor,
                        disabledContentColor = white
                    ),
                    onClick = {
                        onEventDispatcher.invoke(AllCardContract.Intent.OpenAddCardBottomDialog)
                    }) {
                    Text(
                        text = stringResource(id = R.string.add_card),
                        fontFamily = FontFamily(Font(R.font.pnfont_medium))
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun AllCardPreview() {
        MobileBankingTheme {
            AllCardContent(AllCardContract.UIState.InitState, {})
        }
    }
}