package com.example.mobilebanking.presentantion.screen.whatis

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.CardIconTwoText
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.btnInvisibleColor
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white

class WhatIsPaymentCardScreen : Screen {
    @Composable
    override fun Content() {

        val model: WhatIsPaymentCardContract.Model = getViewModel<WhatIsPaymentCardModule>()
        MobileBankingTheme {
            WhatIsContent(model::onEventDispatcher)
        }
    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun WhatIsContent(onEventDispatcher: (WhatIsPaymentCardContract.Intent) -> Unit) {
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
                                onEventDispatcher.invoke(WhatIsPaymentCardContract.Intent.PopBackStack)
                            }),
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = R.drawable.paynet_card_info),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )

                Text(
                    text = stringResource(id = R.string.what_is_payment_card),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 28.sp,
                    color = black,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 18.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.the_funds),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        fontSize = 16.sp,
                        color = black,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.without_commission),
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        fontSize = 16.sp,
                        color = black,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.fill_and_transfer),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        fontSize = 16.sp,
                        color = black,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
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
                        painter = painterResource(R.drawable.ic_operations_top_up_wallet),
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
                            text = stringResource(id = R.string.toFill),
                            modifier = Modifier,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular))
                        )
                        Text(
                            text = stringResource(id = R.string.payment_agent_infokiosk),
                            fontSize = 14.sp,
                            color = textColor,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        )
                        Text(
                            text = stringResource(id = R.string.on_a_bank_card),
                            fontSize = 14.sp,
                            color = textColor,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                CardIconTwoText(
                    onClick = {},
                    textTitle = stringResource(id = R.string.toTransfer),
                    text = stringResource(
                        id = R.string.to_other_paynet_cards
                    ),
                    icon = R.drawable.ic_action_transfers
                )
                Spacer(modifier = Modifier.height(8.dp))
                CardIconTwoText(
                    onClick = {},
                    textTitle = stringResource(id = R.string.cash_withdrawal),
                    text = stringResource(id = R.string.inPaymentAgent),
                    icon = R.drawable.ic_operations_money
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 16.dp)
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
                        painter = painterResource(R.drawable.ic_operations_withdrawal_wallet),
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
                            .padding(end = 12.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.card_payments),
                            modifier = Modifier,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular))
                        )
                        Text(
                            text = stringResource(id = R.string.services_of_paynet_1000),
                            fontSize = 13.sp,
                            color = textColor,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        )
                        Text(
                            text = stringResource(id = R.string.using_a_paynet_card),
                            fontSize = 13.sp,
                            color = textColor,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 16.dp)
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
                        painter = painterResource(R.drawable.ic_operations_cashback_2),
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
                            .padding(end = 10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.up_to_22_cashback),
                            modifier = Modifier,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular))
                        )
                        Text(
                            text = stringResource(id = R.string.pay_by_paynet_card),
                            fontSize = 13.sp,
                            color = textColor,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        )
                        Text(
                            text = stringResource(id = R.string.cashback_to_your),
                            fontSize = 13.sp,
                            color = textColor,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        )
                        Text(
                            text = stringResource(id = R.string.fillWith),
                            fontSize = 13.sp,
                            color = textColor,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        )
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))

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
                        disabledContainerColor = btnInvisibleColor,
                        disabledContentColor = white,
                        contentColor = white,

                        ),
                    onClick = {
                        onEventDispatcher.invoke(WhatIsPaymentCardContract.Intent.OpenConditionScreen)
                    }) {
                    Text(
                        text = stringResource(id = R.string.btn_restrictions_and_conditions),
                        fontFamily = FontFamily(Font(R.font.pnfont_medium))
                    )
                }
            }
        }


    }

    @Preview
    @Composable
    fun WhatIsPreview() {
        MobileBankingTheme {
            WhatIsContent(onEventDispatcher = {})
        }
    }

}