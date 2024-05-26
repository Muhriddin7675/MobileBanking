package com.example.mobilebanking.presentantion.screen.paymentcard

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebanking.R
import com.example.mobilebanking.presentantion.screen.pager.home.Card
import com.example.mobilebanking.presentantion.screen.profil.CartTextComponent
import com.example.mobilebanking.ui.componnent.card.CardIconTwoText
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.buttonInvisibleColor
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.securityCardStartColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white

class PaymentCardScreen : Screen {
    @Composable
    override fun Content() {

        val model: PaymentCardContract.Model = getViewModel<PaymentCardModule>()
        MobileBankingTheme {
            PaymentCardContent(model::onEventDispatcher)
        }
    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun PaymentCardContent(onEventDispatcher: (PaymentCardContract.Intent) -> Unit) {

        val cardNumber by remember { mutableStateOf("9860 7777 7777 7675") }
        val cardSumma by remember { mutableStateOf("100 000 000") }
        Column(
            Modifier
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
                                onEventDispatcher.invoke(PaymentCardContract.Intent.PopBackStack)
                            }),
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.paymentCard),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    color = black,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(206.dp)
                        .shadow(1.dp,
                            RoundedCornerShape(24.dp)
                        )
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(primaryColor, securityCardStartColor),
                            ), shape = RoundedCornerShape(24.dp)
                        )
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(white)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .height(42.dp)
                                .clip(CircleShape)
                                .background(white)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.paynet_logo),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .height(24.dp)
                                    .width(130.dp)
                                    .padding(vertical = 6.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = cardSumma,
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            fontSize = 32.sp,
                            color = white
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text =stringResource(id = R.string.som),
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            fontSize = 32.sp,
                            color = buttonInvisibleColor
                        )
                    }
                    Text(
                        text = cardNumber,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = white,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.wallet),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = white,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 4.dp)
                        .clickable(
                            indication = null,
                            interactionSource = MutableInteractionSource(),
                            enabled = true,
                            onClickLabel = "",
                            onClick = {
                            })
                        .clip(RoundedCornerShape(16.dp))
                        .shadow(1.dp, RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_add_card),
                        contentDescription = "support",
                        modifier = Modifier
                            .padding(start = 16.dp, end = 12.dp)
                            .align(Alignment.CenterVertically)
                            .size(28.dp)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(vertical = 12.dp)
                            .padding(end = 16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.verify_your_identity),
                            modifier = Modifier,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular))
                        )
                        Text(
                            text = stringResource(id = R.string.spending_more_money_on_paynet_card),
                            fontSize = 14.sp,
                            color = textColor,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        )
                        Text(
                            text = stringResource(id = R.string.spending_more_money_on_paynet_card2),
                            fontSize = 14.sp,
                            color = textColor,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                        .padding(top = 4.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .height(80.dp)
                            .padding(4.dp)
                            .weight(1f)
                            .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(white),
                        icon = R.drawable.ic_add,
                        text = R.string.fill
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        modifier = Modifier
                            .height(80.dp)
                            .padding(4.dp)
                            .weight(1f)
                            .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(white),
                        icon = R.drawable.ic_action_transfers,
                        text = R.string.transact
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        modifier = Modifier
                            .height(80.dp)
                            .padding(4.dp)
                            .weight(1f)
                            .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(white),
                        icon = R.drawable.ic_operations_wallet,
                        text = R.string.payment
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                CardIconTwoText(
                    onClick = { /*TODO*/ },
                    textTitle = stringResource(id = R.string.cash_withdrawal),
                    text = stringResource(
                        id = R.string.payment_agent
                    ),
                    icon = R.drawable.ic_operations_money
                )
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .shadow(1.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    CartTextComponent(
                        icon = R.drawable.ic_qr_code,
                        text = stringResource(id = R.string.for_transfer_qrCod),
                        onClick = {
                        })
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                        .shadow(0.4.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    Text(
                        text = stringResource(id = R.string.payments),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        modifier = Modifier.padding(start = 16.dp, top = 12.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.there_is_no_payment_yet),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterHorizontally),
                        color = textColor
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 12.dp)
                        .shadow(1.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    CartTextComponent(
                        icon = R.drawable.ic_document,
                        text = stringResource(id = R.string.offer),
                        onClick = {
                        })
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 12.dp)
                        .shadow(1.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    CartTextComponent(
                        icon = R.drawable.ic_assist,
                        text = stringResource(id = R.string.conditions_and_restrictions),
                        onClick = {
                        })
                }
            }
        }
    }

    @Preview
    @Composable
    fun PaymentCardPreview() {
        MobileBankingTheme {
            PaymentCardContent({})
        }
    }
}