package com.example.mobilebanking.presentantion.screen.pager.transfer

import CardNumberTransformation
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.mobilebank.ui.components.CustomImageView
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.model.TemplateData
import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.presentantion.dialog.DeleteAllUsersDialog
import com.example.mobilebanking.presentantion.dialog.SelectMyCardDialog
import com.example.mobilebanking.ui.componnent.AddItem
import com.example.mobilebanking.ui.componnent.card.UserCardItem
import com.example.mobilebanking.ui.componnent.text.CustomTextView
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.colorInputBg
import com.example.mobilebanking.ui.theme.selectedItemColor
import com.example.mobilebanking.ui.theme.unSelectedItemColor
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

object TransfersPage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(id = R.string.transfers)
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_action_transfers))

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel: TransferContract.ViewModel = getViewModel<TransferViewModel>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        viewModel.onEventDispatcher(TransferContract.Intent.SearchFromLocal(""))
        viewModel.onEventDispatcher(TransferContract.Intent.LoadMyCardsData)
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                TransferContract.SideEffect.OpenMyCardsDialog -> {
                    bottomSheetNavigator.show(
                        SelectMyCardDialog(
                            addClick = {
                                bottomSheetNavigator.hide()
                                viewModel.onEventDispatcher(TransferContract.Intent.NavigateAddCardScreen)
                            },
                            itemClick = {
                                bottomSheetNavigator.hide()
                                viewModel.onEventDispatcher(
                                    TransferContract.Intent.NavigateTransferMyCardScreen(
                                        UserCardData(0, "000800080008${it.pan}", it.owner)
                                    )
                                )
                            }
                        )
                    )
                }

                TransferContract.SideEffect.OpenDeleteAllUsersDialog -> {
                    bottomSheetNavigator.show(
                        DeleteAllUsersDialog(
                            delete = {
                                viewModel.onEventDispatcher(TransferContract.Intent.DeleteAll)
                                bottomSheetNavigator.hide()
                            },
                            dismiss = {
                                bottomSheetNavigator.hide()
                            },
                            blockCancel = {
                                bottomSheetNavigator.hide()
                            }
                        )
                    )
                }
            }
        }
        TransferContent(
            uiState = viewModel.collectAsState().value,
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
private fun TransferContent(
    uiState: TransferContract.UIState,
    onEventDispatcher: (TransferContract.Intent) -> Unit
) {

    val items = List(1) { index -> "Item $index" }
    val ls = listOf(
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),


        )
    var cardNumber by remember { mutableStateOf("") }
    var focusedTextField by remember { mutableStateOf(false) }
    var searchedUser by remember { mutableStateOf(UserCardData(0, "", "")) }
    val focusManager = LocalFocusManager.current
    var visibility by remember { mutableStateOf(false) }
    var mycards by remember { mutableStateOf(listOf<CardData>()) }
    var searchedItems by remember { mutableStateOf(listOf<UserCardData>()) }
    var errorMes by remember { mutableStateOf(false) }

    when (uiState) {
        is TransferContract.UIState.SearchedItems -> {
            searchedItems = (uiState as TransferContract.UIState.SearchedItems).ls
        }

        is TransferContract.UIState.RemoteSearchItem -> {
            errorMes = false
            searchedUser = (uiState as TransferContract.UIState.RemoteSearchItem).data
        }

        is TransferContract.UIState.ErrorMes -> {
            errorMes = true
        }

        is TransferContract.UIState.MyCardsData -> {
            mycards = (uiState as TransferContract.UIState.MyCardsData).ls
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .background(appBackgroundColorWhite)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(56.dp)
            ) {
                CustomTextView(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterStart),
                    text = stringResource(id = R.string.transfers),
                    fontSize = 28,
                    color = Color.Black,
                )
                this@Column.AnimatedVisibility(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .align(Alignment.CenterEnd),
                    visible = visibility,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
                ) {
                    Button(
                        onClick = {
                            visibility = false
                            focusManager.clearFocus()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(3.dp)
                    ) {
                        CustomTextView(
                            text = "Bekor qilish",
                            fontSize = 14
                        )
                    }
                }
            }

        }

        LazyColumn(
            modifier = Modifier
                .padding(bottom = 56.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 16.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(color = colorInputBg, shape = RoundedCornerShape(16.dp))
                ) {
                    BasicTextField(
                        value = cardNumber,
                        onValueChange = {
                            if (it.all { char -> char.isDigit() } && it.length <= 16) {
                                cardNumber = it
                                onEventDispatcher(TransferContract.Intent.SearchFromLocal(cardNumber))
                                if (it.length == 16) {
                                    onEventDispatcher(TransferContract.Intent.GetCardOwner(cardNumber))
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .onFocusChanged { focusState ->
                                focusedTextField = focusState.isFocused
                                if (focusState.hasFocus) {
                                    visibility = true
                                }
                            }
                            .padding(start = 16.dp)
                            .width(280.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = CardNumberTransformation,
                        keyboardActions = KeyboardActions.Default,
                        cursorBrush = SolidColor(selectedItemColor),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            fontWeight = FontWeight(600),
                        )
                    )

                    if (cardNumber.isEmpty()) {
                        CustomTextView(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .align(Alignment.CenterStart),
                            text = stringResource(id = R.string.card_or_phone),
                            color = unSelectedItemColor,
                            fontSize = 18,
                            fontWeight = 600,
                        )
                    }

                    CustomImageView(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                            .size(25.dp),
                        src = R.drawable.ic_action_scan_card,
                    )
                }
            }

            if (!focusedTextField) {

                item {
                    Column(
                        modifier = Modifier
                            .padding(top = 24.dp)
                    ) {
                        CustomTextView(
                            text = stringResource(id = R.string.templates),
                            fontSize = 20,
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        LazyRow {
                            items(ls.size) {
                                AddItem(
                                    modifier = Modifier
                                        .height(116.dp)
                                        .width(90.dp),
                                    text = ls[it].text,
                                    icon = ls[it].icon
                                )
                            }
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .fillMaxHeight()
                                .weight(0.5F)
                                .clickable(
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    },
                                    indication = null
                                ) { onEventDispatcher(TransferContract.Intent.OpenMyCardsDialog) },
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFDCF4)),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                CustomImageView(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .size(100.dp),
                                    src = R.drawable.self_transfer_to_card
                                )
                                CustomTextView(
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                        .align(Alignment.CenterStart)
                                        .width(84.dp),
                                    text = stringResource(id = R.string.to_my_card),
                                    fontSize = 14,
                                    maxLines = 2,
                                    textAlign = TextAlign.Left,
                                    lineHeight = 17F
                                )
                            }
                        }

                        Card(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .fillMaxHeight()
                                .weight(0.5F),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF90FFE2)),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                CustomImageView(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .size(120.dp),
                                    src = R.drawable.self_transfer_to_wallet
                                )
                                CustomTextView(
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                        .align(Alignment.CenterStart)
                                        .width(84.dp),
                                    maxLines = 2,
                                    text = stringResource(id = R.string.to_paynet_card),
                                    textAlign = TextAlign.Left,
                                    fontSize = 14,
                                    lineHeight = 17F
                                )
                            }
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .padding(top = 24.dp)
                    ) {
                        CustomTextView(
                            text = stringResource(id = R.string.templates),
                            fontSize = 20,
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        LazyRow {
                            items(ls.size) {
                                AddItem(
                                    modifier = Modifier
                                        .height(116.dp)
                                        .width(90.dp),
                                    text = ls[it].text,
                                    icon = ls[it].icon
                                )
                            }
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 24.dp)
                    ) {
                        CustomTextView(
                            text = stringResource(id = R.string.international_transfers),
                            fontSize = 20
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 16.dp, bottom = 16.dp)
                                .fillMaxWidth()
                                .height(140.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .fillMaxHeight()
                                    .weight(0.5F),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFCBF5FE)),
                                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    CustomImageView(
                                        modifier = Modifier
                                            .align(Alignment.BottomCenter)
                                            .padding(top = 32.dp)
                                            .size(130.dp),
                                        src = R.drawable.uzb_to_ru
                                    )
                                    CustomTextView(
                                        modifier = Modifier
                                            .padding(start = 16.dp, top = 12.dp),
                                        text = stringResource(id = R.string.to_ru),
                                        fontSize = 18,
                                        textAlign = TextAlign.Left,
                                        lineHeight = 17F
                                    )
                                }
                            }

                            Card(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .fillMaxHeight()
                                    .weight(0.5F),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4EFFF)),
                                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    CustomImageView(
                                        modifier = Modifier
                                            .align(Alignment.BottomCenter)
                                            .padding(top = 32.dp)
                                            .size(130.dp),
                                        src = R.drawable.ru_to_uzb
                                    )
                                    CustomTextView(
                                        modifier = Modifier
                                            .padding(start = 16.dp, top = 12.dp),
                                        text = stringResource(id = R.string.to_uz),
                                        fontSize = 18,
                                        textAlign = TextAlign.Left,
                                        lineHeight = 17F
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(bottom = 64.dp))
                    }
                }
            } else {
                if (cardNumber.length < 16 && !errorMes) {
                    if (searchedItems.isNotEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(bottom = 8.dp)
                                    .fillMaxWidth()
                            ) {
                                CustomTextView(
                                    modifier = Modifier
                                        .align(Alignment.CenterStart),
                                    text = stringResource(id = R.string.last),
                                    fontSize = 16,
                                    fontWeight = 600
                                )

                                Row(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .clickable(
                                            interactionSource = remember {
                                                MutableInteractionSource()
                                            },
                                            indication = null
                                        ) { onEventDispatcher(TransferContract.Intent.OpenDeleteAllUsersDialog) },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CustomTextView(
                                        text = stringResource(id = R.string.delete_all),
                                        fontSize = 16,
                                    )

                                    CustomImageView(
                                        modifier = Modifier
                                            .padding(start = 4.dp)
                                            .size(18.dp),
                                        src = R.drawable.ic_action_trash
                                    )
                                }
                            }
                        }
                    }
                    items(searchedItems.size) {
                        UserCardItem(
                            modifier = Modifier
                                .padding(bottom = 6.dp, top = 4.dp)
                                .clickable(
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    },
                                    indication = null
                                ) {
                                    onEventDispatcher(
                                        TransferContract.Intent.NavigateTransferCardScreen(
                                            userCardData = searchedItems[it]
                                        )
                                    )
                                },
                            data = searchedItems[it]
                        )
                    }
                } else if (cardNumber.length == 16 && !errorMes) {
                    item {
                        UserCardItem(
                            modifier = Modifier
                                .padding(bottom = 6.dp, top = 4.dp)
                                .clickable(
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    },
                                    indication = null
                                ) {
                                    onEventDispatcher(
                                        TransferContract.Intent.NavigateTransferCardScreen(
                                            userCardData = searchedUser
                                        )
                                    )
                                },
                            data = searchedUser
                        )
                    }
                } else if (errorMes) {
                    item {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 8.dp, top = 4.dp)
                                .fillMaxWidth()
                                .height(200.dp),
                            colors = CardDefaults
                                .cardColors(Color.White),
                            elevation = CardDefaults
                                .elevatedCardElevation(3.dp)
                        ) {
                            CustomImageView(
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .size(80.dp),
                                src = R.drawable.provider_not_found
                            )
                            CustomTextView(
                                modifier = Modifier
                                    .padding(top = 24.dp)
                                    .align(Alignment.CenterHorizontally),
                                text = stringResource(id = R.string.we_cant_find),
                                fontSize = 20,
                                fontWeight = 600
                            )
                            CustomTextView(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                text = stringResource(id = R.string.card_number_wrong),
                                fontSize = 16
                            )
                        }
                    }
                }
            }
        }

    }


}


@Composable
@Preview
private fun PaymentContentPreview() {
    MobileBankingTheme {
        TransferContent(uiState = TransferContract.UIState.MyCardsData()) {}
    }
}


