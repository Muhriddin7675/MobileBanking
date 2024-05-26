package com.example.mobilebanking.presentantion.screen.transfer_card

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.presentantion.dialog.SelectMyCardDialog
import com.example.mobilebanking.ui.componnent.text.CustomTextView
import com.example.mobilebanking.ui.componnent.card.MyCardItem
import com.example.mobilebanking.ui.componnent.card.UserCardItem
import com.example.mobilebanking.ui.componnent.card.CustomAppBar
import com.example.mobilebanking.ui.componnent.card.CustomButton
import com.example.mobilebanking.ui.componnent.text.CustomTextField
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
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

class TransferCardScreen(
    private val userCardData: UserCardData,
) : Screen {
    @Composable
    override fun Content() {
        val viewModel: TransferCardContract.ViewModel = getViewModel<TransferCardViewModel>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        viewModel.onEventDispatcher(TransferCardContract.Intent.LoadCardsData)

        viewModel.collectSideEffect { sideEffect ->
            when(sideEffect) {
                TransferCardContract.SideEffect.OpenSelectMyCardDialog -> {
                    bottomSheetNavigator.show(
                        SelectMyCardDialog(
                            addClick = {
                                bottomSheetNavigator.hide()
                                viewModel.onEventDispatcher(TransferCardContract.Intent.NavigateAddCardScreen)
                            },
                            itemClick = {
                                bottomSheetNavigator.hide()
                                viewModel.onEventDispatcher(TransferCardContract.Intent.SelectedCardData(it))
                            }
                        )
                    )
                }
            }
        }
        TransferCardContent(
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher,
            userCardData = userCardData
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransferCardContent(
    uiState: State<TransferCardContract.UIState>,
    onEventDispatcher: (TransferCardContract.Intent) -> Unit,
    userCardData: UserCardData
) {
    var amount by remember { mutableStateOf("") }
    var amountIsCorrect by remember { mutableStateOf(false) }
    var balanceIsCorrect by remember { mutableStateOf(true) }
    var selectedCard by remember { mutableStateOf(CardData(0, "", 0, "", "", "20", 0, 1, false)) }
    val amountColor = remember(amount) {
        if (amount.isNotEmpty()) {
            if (moneyAmountIsCorrect(amount)) Color.Black else Color(0xFFC55252)
        } else Color.Black
    }
    var percent by remember { mutableStateOf(0) }
    percent = if (amount.isNotEmpty() && moneyAmountIsCorrect(amount)) amount.toInt() / 100  else 0

    when(uiState.value) {
        is TransferCardContract.UIState.SelectedCardData-> {
            selectedCard = (uiState.value as TransferCardContract.UIState.SelectedCardData).data
        }
        else -> {}
    }

    CustomAppBar(
        title = stringResource(id = R.string.transfer_to_card),
        onClick = {
            onEventDispatcher(TransferCardContract.Intent.BackTransferScreen)
        }) {
        Box(
            modifier = Modifier
                .background(appBackgroundColorWhite)
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

                myLog(selectedCard.amount.toString())

                MyCardItem(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null
                        ) {
                            onEventDispatcher(TransferCardContract.Intent.OpenSelectMyCardDialog)
                        },
                    data = selectedCard,
                )

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
                            onEventDispatcher(TransferCardContract.Intent.BackTransferScreen)
                        },
                    data = userCardData
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
                            if (value.length <= 8 && value.isDigitsOnly()) {
                                amount = value
                                amountIsCorrect = if (value == "") false else moneyAmountIsCorrect(value)
                                balanceIsCorrect = if (amount.isNotEmpty()) selectedCard.amount > amount.toLong() else true
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
                        text = "${stringResource(id = R.string.transfer_percent)} 1% - $percent ${stringResource(id = R.string.som)}"
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
                enabled = amount.length > 3 && balanceIsCorrect,
                click = {
                    onEventDispatcher(TransferCardContract.Intent.TransferMoney(selectedCard.id.toString(), userCardData.pan, amount.toLong(), percent.toLong(), userCardData))
                })
        }
    }

}

