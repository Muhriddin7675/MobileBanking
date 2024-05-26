package com.example.mobilebanking.presentantion.screen.to_mycard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.presentantion.dialog.MyCardsDialog
import com.example.mobilebanking.ui.componnent.card.AddCardItem
import com.example.mobilebanking.ui.componnent.text.CustomTextView
import com.example.mobilebanking.ui.componnent.card.MyCardItem
import com.example.mobilebanking.ui.componnent.card.UserCardItem
import com.example.mobilebanking.ui.componnent.card.CustomAppBar
import com.example.mobilebanking.ui.componnent.card.CustomButton
import com.example.mobilebanking.ui.componnent.text.CustomTextField
import com.example.mobilebanking.ui.theme.btnInvisibleColor
import com.example.mobilebanking.ui.theme.colorInputBg
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.selectedItemColor
import com.example.mobilebanking.ui.theme.unSelectedItemColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.ui.transformation.MoneyFormatTransformation
import com.example.mobilebanking.util.moneyAmountIsCorrect
import com.example.mobilebanking.util.myLog
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class TransferMyCardScreen(
    private val list: List<CardData>,
    private val data: UserCardData
) : Screen {
    @Composable
    override fun Content() {
        val viewModel: TransferMyCardContract.ViewModel = getViewModel<TransferMyCardViewModel>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        viewModel.collectSideEffect { sideEffect ->
            when(sideEffect) {
                is TransferMyCardContract.SideEffect.OpenSelectMyCardDialog -> {
                    bottomSheetNavigator.show(
                        MyCardsDialog(
                            list = sideEffect.list,
                            type = sideEffect.type,
                            itemClick = { cardData, i ->
                                bottomSheetNavigator.hide()
                                viewModel.onEventDispatcher(
                                    TransferMyCardContract.Intent.SelectedItem(
                                        cardData,
                                        i
                                    )
                                )
                            }
                        )
                    )
                }
            }
        }
        TransferMyCardContent(
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher,
            data = data,
            list
        )
    }
}

@Composable
fun TransferMyCardContent(
    uiState: State<TransferMyCardContract.UIState>,
    onEventDispatcher: (TransferMyCardContract.Intent) -> Unit,
    data: UserCardData,
    list: List<CardData>
) {
    var amount by remember { mutableStateOf("") }
    var amountIsCorrect by remember { mutableStateOf(false) }
    var balanceIsCorrect by remember { mutableStateOf(true) }
    var itemsEquals by remember {
        mutableStateOf(false)
    }
    var selectedCardTo by remember {
        mutableStateOf(CardData(-1, "", 0, "", "", "0", 0, 0, false))
    }
    var selectedCardFrom by remember {
        mutableStateOf(data)
    }
    val amountColor = remember(amount) {
        if (amount.isNotEmpty()) {
            if (moneyAmountIsCorrect(amount)) Color.Black else Color(0xFFC55252)
        } else Color.Black
    }
    var percent by remember { mutableStateOf(0) }
    percent = if (amount.isNotEmpty() && moneyAmountIsCorrect(amount)) amount.toInt() / 100  else 0

    when(uiState.value) {
        is TransferMyCardContract.UIState.SelectedCardTo -> {
            selectedCardTo = (uiState.value as TransferMyCardContract.UIState.SelectedCardTo).data
        }
        is TransferMyCardContract.UIState.SelectedCardFrom -> {
            val data = (uiState.value as TransferMyCardContract.UIState.SelectedCardFrom).data
            selectedCardFrom = UserCardData(data.id.toInt(), "000800080008${data.pan}", data.owner)
        }
    }

    CustomAppBar(
        title = stringResource(id = R.string.transfer_to_card),
        onClick = {
            onEventDispatcher(TransferMyCardContract.Intent.BackTransferScreen)
        }) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(it)
                .fillMaxSize()
        ) {

            Column {
                CustomTextView(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 0.dp),
                    text = stringResource(id = R.string.from),
                    fontSize = 20
                )

                if (selectedCardTo.id == -1) {
                    AddCardItem(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null
                            ) {
                                onEventDispatcher(
                                    TransferMyCardContract.Intent.OpenSelectMyCardDialog(
                                        list.filter { it != selectedCardTo },
                                        0
                                    )
                                )
                            }
                    )
                } else {
                    MyCardItem(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null
                            ) {
                                onEventDispatcher(
                                    TransferMyCardContract.Intent.OpenSelectMyCardDialog(
                                        list.filter { it != selectedCardTo },
                                        0
                                    )
                                )
                            },
                        data = selectedCardTo,
                    )
                }

                CustomTextView(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 36.dp),
                    text = stringResource(id = R.string.to),
                    fontSize = 20
                )

                UserCardItem(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null
                        ) {
                            onEventDispatcher(
                                TransferMyCardContract.Intent.OpenSelectMyCardDialog(list, 1)
                            )
                        },
                    data = selectedCardFrom
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 36.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(color = colorInputBg, shape = RoundedCornerShape(16.dp))
                ) {
                    CustomTextField(
                        value = amount,
                        onValueChange = {value ->
                            myLog(value)
                            if (value.length <= 8 && value.isDigitsOnly()) {
                                if (value.isNotEmpty()) {
                                    itemsEquals = selectedCardFrom.id == selectedCardTo.id.toInt()
                                } else itemsEquals = false
                                amount = value
                                amountIsCorrect = if (value == "") false else moneyAmountIsCorrect(value)
                                balanceIsCorrect = if (amount.isNotEmpty()) selectedCardTo.amount > amount.toLong() else true
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 16.dp, top = 16.dp)
                            .width(280.dp),
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,
                        visualTransformation = MoneyFormatTransformation(),
                        keyboardActions = KeyboardActions.Default,
                        cursorBrush = SolidColor(selectedItemColor),
                        fontSize = 18,
                        fontWeight = 600,
                        color = amountColor
                    )

                    CustomTextView(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 6.dp),
                        text = stringResource(id = R.string.you_transfer),
                        color = unSelectedItemColor,
                        fontSize = 14,
                        fontWeight = 600,
                    )

                    if (amount.isEmpty()) {
                        CustomTextView(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp, top = 16.dp),
                            text = "1000 - 12 400 000",
                            color = unSelectedItemColor,
                            fontSize = 18,
                            fontWeight = 600,
                        )
                    }
                }

                if (balanceIsCorrect) {
                    CustomTextView(
                        modifier = Modifier.padding(start = 16.dp, top = 12.dp),
                        text = if (itemsEquals) stringResource(id = R.string.error_to_my) else "${stringResource(id = R.string.transfer_percent)} 1% - $percent ${stringResource(id = R.string.som)}",
                        color = if (itemsEquals) Color(0xFFF07575) else Color.Black
                    )
                } else {
                    CustomTextView(
                        modifier = Modifier.padding(start = 16.dp, top = 12.dp),
                        text = stringResource(id = R.string.amount_is_less),
                        color = Color(0xFFC55252)
                    )
                }

            }

            CustomButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp, top = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                text = stringResource(id = R.string.btn_continue),
                fontSize = 20,
                fontWeight = 700,
                textColor = Color.White,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = btnInvisibleColor,
                    disabledContentColor = white,
                    contentColor = white,
                    containerColor = primaryColor
                ),
                enabled = amount.length > 3 && balanceIsCorrect && !itemsEquals,
                click = {
                    onEventDispatcher(
                        TransferMyCardContract.Intent.TransferMoney(
                            selectedCardTo.id.toString(),
                            data.pan,
                            amount.toLong(),
                            percent.toLong(),
                            data
                        )
                    )
                })
        }
    }

}

@Preview
@Composable
fun TransferPreveiew() {

}