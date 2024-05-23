package com.example.mobilebanking.presentantion.screen.pager.home

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.presentantion.dialog.AddCardBottomDialog
import com.example.mobilebanking.ui.componnent.CardAvia
import com.example.mobilebanking.ui.componnent.CardMIB
import com.example.mobilebanking.ui.componnent.CardMyHouse
import com.example.mobilebanking.ui.componnent.OneAddCardComponent
import com.example.mobilebanking.ui.componnent.TwoAddCardComponent
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.circleStartColorGreen
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.securityCardEndColor
import com.example.mobilebanking.ui.theme.securityCardStartColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.addSpacesEveryAmount
import com.example.mobilebanking.util.formatPhoneNumber
import com.example.mobilebanking.util.openWebsite
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

object HomePage : Tab {
    override val options: TabOptions
        @Composable get() {
            val title = "Asosiy"
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_operations_home))

            return remember {
                TabOptions(
                    index = 2u, title = title, icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        MobileBankingTheme {

            val screenModel: HomeContract.Model = getViewModel<HomeModel>()

            val uiState = screenModel.collectAsState()
            HomeScreenContent(
                uiState = uiState.value, onEventDispatcher = screenModel::onEventDispatcher
            )

            val bottomNavigator = LocalBottomSheetNavigator.current
            screenModel.collectSideEffect { sideEffect ->
                when (sideEffect) {
                    HomeContract.SideEffect.OpenAddCardBottomDialog -> {
                        bottomNavigator.show(AddCardBottomDialog(clickUzCard = {
                            bottomNavigator.hide()
                            screenModel.onEventDispatcher(HomeContract.Intent.OpenAddCardScreen)
                        }, clickRuCard = {
                            bottomNavigator.hide()
                        }))
                    }
                }
            }
        }
    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun HomeScreenContent(
        uiState: HomeContract.UIState, onEventDispatcher: (HomeContract.Intent) -> Unit
    ) {


        var cardList by remember { mutableStateOf(listOf<CardData>()) }
        var phoneNumber by remember { mutableStateOf("") }
        var showBalans by remember { mutableStateOf(true) }
        var totalBalance by remember { mutableStateOf("0") }

        onEventDispatcher.invoke(HomeContract.Intent.GetData)

//        LaunchedEffect(Unit) {
        onEventDispatcher.invoke(HomeContract.Intent.GetAllCard)
//        }
        when (uiState) {
            is HomeContract.UIState.GetUIState -> {
                phoneNumber = uiState.phone
                showBalans = uiState.showBalance
            }

            is HomeContract.UIState.LoadCardData -> {
                totalBalance = uiState.totalAmount.toString().addSpacesEveryAmount()
                cardList = uiState.cardList
            }
        }
        val context = LocalContext.current
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(appBackgroundColorWhite)
                    .clickable(interactionSource = MutableInteractionSource(),
                        indication = null,
                        enabled = true,
                        onClickLabel = null,
                        onClick = {
                            onEventDispatcher.invoke(HomeContract.Intent.OpenProfilScreen)
                        })

            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(circleStartColorGreen)
                        .align(Alignment.CenterVertically)


                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = phoneNumber.formatPhoneNumber(),
                    Modifier.align(Alignment.CenterVertically),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 16.sp,
                    color = black
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = "",
                    Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .padding(end = 16.dp)
                ) {
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        Image(painter = painterResource(id = R.drawable.ic_operation_support),
                            contentDescription = "",
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    openWebsite(context, "http://chat.paynet.uz")
                                })
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_operations_bell_new),
                            contentDescription = ""
                        )
                    }
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(appBackgroundColorWhite)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.my_money),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = textColor
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Text(
                            text = if (showBalans) totalBalance else "• •••", fontSize = 28.sp,
                            fontFamily = FontFamily(
                                Font(R.font.pnfont_semibold),
                            ),
                            color = black

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.som),
                            fontSize = 28.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            color = textColor
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                        ) {
                            Image(
                                painter = painterResource(id = if (showBalans) R.drawable.ic_action_eye_open else R.drawable.ic_action_eye_close),
                                contentDescription = "eye",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        showBalans = !showBalans
                                        onEventDispatcher.invoke(
                                            HomeContract.Intent.ChangeShowBalanceState(
                                                showBalans
                                            )
                                        )
                                    }
                            )

                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .clickable(interactionSource = MutableInteractionSource(),
                            indication = null,
                            enabled = true,
                            onClickLabel = null,
                            onClick = {
                                onEventDispatcher.invoke(HomeContract.Intent.OpenPaymentCardScreen)
                            })
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                            text = stringResource(id = R.string.pay_net_card),
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            color = Color.Black
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .padding(end = 12.dp, top = 12.dp)
                                    .align(Alignment.CenterEnd)
                                    .clickable(interactionSource = MutableInteractionSource(),
                                        indication = null,
                                        enabled = true,
                                        onClickLabel = null,
                                        onClick = {
                                            onEventDispatcher.invoke(HomeContract.Intent.OpenWhatIsPaymentCard)
                                        }),
                                text = stringResource(id = R.string.what_is_it),
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                color = circleStartColorGreen
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 9.dp, top = 4.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .width(72.dp)
                                .height(56.dp),
                            painter = painterResource(id = R.drawable.ic_paynet),
                            contentDescription = ""
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                                .padding(start = 4.dp)
                        ) {

                            Text(
                                text = stringResource(id = R.string.pay_net_card) + " • 7675",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                color = textColor
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = if (showBalans) "0" else "• •••",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                    color = black
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(id = R.string.som),
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                    color = textColor
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 6.dp, end = 6.dp, bottom = 8.dp, top = 4.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(4.dp)
                                .weight(1f)
                                .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white),
                            icon = R.drawable.ic_add,
                            text = R.string.fill
                        )
                        Card(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(4.dp)
                                .weight(1f)
                                .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white),
                            icon = R.drawable.ic_action_transfers,
                            text = R.string.transact
                        )
                        Card(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(4.dp)
                                .weight(1f)
                                .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white),
                            icon = R.drawable.ic_operations_wallet,
                            text = R.string.payment
                        )
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 9.dp, top = 4.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .width(72.dp)
                                .height(82.dp),
                            painter = painterResource(id = R.drawable.paynetjon_promotion),
                            contentDescription = ""
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                                .padding(start = 4.dp, top = 4.dp)
                        ) {

                            Text(
                                text = stringResource(id = R.string.your_level),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                color = textColor
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "0",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                    color = black
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(id = R.string.coin),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                    color = textColor,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, end = 16.dp, bottom = 4.dp)
                                    .clip(CircleShape),
                                progress = 0f,
                                color = primaryColor,
                                trackColor = white,
                            )
                            Text(
                                text = "100 qoldi keyingi boshqichga",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                color = textColor
                            )

                        }

                    }
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, bottom = 10.dp, top = 4.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.exchange),
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(securityCardStartColor, securityCardEndColor)
                            )
                        )
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                                text = stringResource(id = R.string.paymentSecurity),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                            )
                            Text(
                                text = stringResource(id = R.string.increasingPoverty),
                                modifier = Modifier.padding(start = 12.dp),
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular))
                            )
                            Text(
                                text = stringResource(id = R.string.do_you_want),
                                modifier = Modifier.padding(start = 12.dp),
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular))
                            )
                            Button(
                                modifier = Modifier
                                    .padding(
                                        start = 12.dp, bottom = 12.dp, top = 8.dp
                                    )
                                    .height(36.dp),
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(white),

                                ) {
                                Text(
                                    text = stringResource(id = R.string.verify_your_identity),
                                    color = Color.Black,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.7f)
                                .align(Alignment.CenterVertically)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.securety_identification),
                                contentDescription = "security",
                                modifier = Modifier.align(Alignment.Center)
                            )

                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(end = 6.dp)
                            .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(white)
                            .clickable(interactionSource = MutableInteractionSource(),
                                indication = null,
                                enabled = true,
                                onClickLabel = "",
                                onClick = {})
                    ) {
                        Row {
                            Text(
                                modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                                text = stringResource(id = R.string.my_cards),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                color = black
                            )
                            if (cardList.isNotEmpty()) {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .clip(
                                            CircleShape
                                        )
                                        .background(primaryColor)
                                        .padding(horizontal = 3.dp),
                                    text = cardList.size.toString(),
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                    color = white
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            if (cardList.size >= 2) {
                                Row {
                                    Image(painter = painterResource(id = R.drawable.ic_add),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(top = 12.dp, end = 12.dp)
                                            .size(16.dp)
                                            .align(Alignment.CenterVertically)
                                            .clip(
                                                CircleShape
                                            )
                                            .clickable {
                                                onEventDispatcher.invoke(HomeContract.Intent.OpenAddCardBottomDialog)
                                            })
                                    Image(painter = painterResource(id = R.drawable.ic_right),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(top = 12.dp, end = 12.dp)
                                            .size(16.dp)
                                            .align(Alignment.CenterVertically)
                                            .clip(
                                                CircleShape
                                            )
                                            .clickable {
                                                onEventDispatcher.invoke(HomeContract.Intent.OpenAllCardScreen)
                                            })
                                }
                            }
                        }

                        if (cardList.isEmpty()) {
                            AddCardComponent(addCardClick = {
                                onEventDispatcher.invoke(HomeContract.Intent.OpenAddCardBottomDialog)
                            })

                        } else if (cardList.size == 1) {
                            OneAddCardComponent(
                                showBalance = showBalans,
                                addCardClick = {
                                    onEventDispatcher.invoke(HomeContract.Intent.OpenAddCardBottomDialog)
                                }, cardClick = {
                                    onEventDispatcher.invoke(
                                        HomeContract.Intent.OpenCardDetailScreen(it)
                                    )

                                }, cardData = cardList[0]
                            )
                        } else {
                            TwoAddCardComponent(
                                showBalance = showBalans,
                                cardClick = {
                                    onEventDispatcher.invoke(
                                        HomeContract.Intent.OpenCardDetailScreen(
                                            it
                                        )
                                    )
                                }, cardList = cardList
                            )
                        }


                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(start = 6.dp)
                            .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(color = white)
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                            text = stringResource(id = R.string.cashback_account),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            color = black

                        )
                        Text(
                            modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                            text = stringResource(id = R.string.balance),
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                            color = textColor,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = if (showBalans) "0" else "• •••", fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(id = R.string.som),
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                color = textColor
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 8.dp, end = 8.dp, start = 8.dp
                                    )
                                    .height(24.dp)

                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    text = stringResource(R.string.today),
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                    color = black
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text(
                                        text = "0 so'm",
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                        color = white,
                                        modifier = Modifier
                                            .padding(
                                                start = 4.dp, end = 4.dp, top = 3.dp, bottom = 3.dp
                                            )
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(circleStartColorGreen)
                                            .align(Alignment.CenterEnd)

                                    )
                                }
                            }
                        }

                    }
                }

                CardAvia(clickButton = {}, clickAviaCard = {})
                CardMyHouse()
                CardMIB(clickRoot = {
                    onEventDispatcher(HomeContract.Intent.OpenIdentityVerificationScreen)
                })
                Spacer(modifier = Modifier.height(56.dp))


            }

        }
    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun AddCardComponent(
        addCardClick: () -> Unit
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(white)
                .clickable(interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = true,
                    onClickLabel = null,
                    role = null,
                    onClick = {
                        addCardClick.invoke()
                    })
        ) {
            Column(Modifier.align(Alignment.Center)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add_card),
                    contentDescription = "add card",
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.add_card),
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(
                        Font(R.font.pnfont_regular)
                    ),
                )
            }
        }
    }


}

@Composable
fun Card(
    modifier: Modifier, @DrawableRes icon: Int, @StringRes text: Int
) {
    Box(
        modifier = modifier
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(icon),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                text = stringResource(id = text),
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMainScreen() {
    MobileBankingTheme {
        HomePage.HomeScreenContent(HomeContract.UIState.GetUIState()) {}
    }

}