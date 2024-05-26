package com.example.mobilebanking.presentantion.screen.coditions

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.btnInvisibleColor
import com.example.mobilebanking.ui.theme.gray
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.textLinkColor
import com.example.mobilebanking.ui.theme.white

class ConditionsScreen : Screen {
    @Composable
    override fun Content() {

        val model: ConditionsContract.Model = getViewModel<ConditionsModel>()

        MobileBankingTheme {
            ConditionsContent(model::onEventDispatcher)
        }
    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun ConditionsContent(onEventDispatcher: (ConditionsContract.Intent) -> Unit) {
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
                                onEventDispatcher.invoke(ConditionsContract.Intent.PopBackStack)
                            }),

                    )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.conditions_and_restrictions),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    color = black,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }


            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = stringResource(id = R.string.confirm_your_identity_paynet_card),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    fontSize = 16.sp,
                    color = black
                )
                Text(
                    text = stringResource(id = R.string.verifyYourIdentity),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    fontSize = 16.sp, color = black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(Modifier.fillMaxWidth()) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(148.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(bottom = 16.dp, top = 8.dp)
                                .shadow(elevation = 1.5.dp, shape = RoundedCornerShape(20.dp))
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(appBackgroundColorWhite)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.sad_emoji),
                                contentDescription = "uz",
                                modifier = Modifier
                                    .padding(start = 12.dp, top = 18.dp)
                                    .size(32.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = stringResource(id = R.string.your_situation),
                                modifier = Modifier.padding(start = 12.dp),
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                fontSize = 16.sp
                            )
                            Text(
                                text = stringResource(id = R.string.anonymous),
                                modifier = Modifier.padding(start = 12.dp, bottom = 12.dp),
                                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                fontSize = 14.sp,
                                color = textColor

                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(bottom = 16.dp, top = 8.dp)
                                .shadow(elevation = 1.dp, shape = RoundedCornerShape(20.dp))
                                .clip(shape = RoundedCornerShape(20.dp))
                                .background(white)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.emoje_glas),
                                contentDescription = "uz",
                                modifier = Modifier
                                    .padding(start = 12.dp, top = 18.dp)
                                    .size(32.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = stringResource(id = R.string.improve_the_situation),
                                modifier = Modifier.padding(start = 12.dp),
                                color = primaryColor,
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                fontSize = 16.sp
                            )
                            Text(
                                text = stringResource(id = R.string.confirmed),
                                modifier = Modifier.padding(start = 12.dp, bottom = 12.dp),
                                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                fontSize = 14.sp,
                                color = textColor
                            )
                        }
                    }

                    Text(
                        text = stringResource(id = R.string.useful),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        fontSize = 14.sp,
                        color = white,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clip(CircleShape)
                            .background(primaryColor)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                            .align(Alignment.TopEnd)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.how_much_can_stored),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 16.sp, color = black
                )
                TwoSumRow(sum1 = stringResource(id = R.string.sum_1700_000), sum2 = stringResource(
                    id = R.string.sum_170_000_000,
                ),boolean = true)

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = stringResource(id = R.string.how_much_spend_month),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 16.sp, color = black
                )
                Text(
                    text = stringResource(id = R.string.payments_transfers_and_cash),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    fontSize = 16.sp, color = textColor
                )

                TwoSumRow(sum1 = stringResource(id = R.string.sum_1700_000), sum2 = stringResource(
                    id = R.string.sum_170_000_000
                ),boolean = true)


                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = stringResource(id = R.string.how_much_you_spend),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 16.sp, color = black
                )

                Text(
                    text = stringResource(id = R.string.possible),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 16.sp, color = black
                )
                Text(
                    text = stringResource(id = R.string.maximum_amount_for_payment),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    fontSize = 16.sp, color = textColor
                )
                TwoSumRow(sum1 = stringResource(id = R.string.sum_340_000), sum2 = stringResource(
                    id = R.string.sum_34_000_000
                ),boolean = true)

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = stringResource(id = R.string.cash_can_withdrawn),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 16.sp, color = black
                )
                TwoSumRow(sum1 = stringResource(id = R.string.no), sum2 = stringResource(
                    id = R.string.yes
                ),boolean = false)

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = stringResource(id = R.string.transferToUzCard_HUMO),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 16.sp, color = black
                )
                TwoSumRow(sum1 = stringResource(id = R.string.no), sum2 = stringResource(
                    id = R.string.yes
                ),boolean = false)

                Text(
                    text = stringResource(id = R.string.commission09),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).align(Alignment.End),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    fontSize = 15.sp, color = textColor,

                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(id = R.string.can_transferred_Paynet_cards),
                    modifier = Modifier.padding(start = 16.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 16.sp, color = black
                )
                TwoSumRow(sum1 = stringResource(id = R.string.no_commission), sum2 = stringResource(
                    id = R.string.no_commission
                ),boolean = false)


                Text(
                    text = stringResource(id = R.string.detailed_conditions),
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 28.dp),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 16.sp, color = textLinkColor
                )
                Spacer(modifier = Modifier.height(12.dp))



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
                        onEventDispatcher.invoke(ConditionsContract.Intent.OpenIdentityVerificationScreen)
                    }) {
                    Text(
                        text = stringResource(id = R.string.verify_your_identity),
                        fontFamily = FontFamily(Font(R.font.pnfont_medium))
                    )
                }
            }


        }

    }

    @Preview
    @Composable
    fun ConditionsPreview() {
        MobileBankingTheme {
            ConditionsContent(onEventDispatcher = {})
        }
    }
    @Composable
    fun TwoSumRow(sum1:String,sum2:String,boolean: Boolean){
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)) {
            Box(
                modifier = Modifier
                    .border(1.dp, shape = CircleShape, color = gray)
                    .weight(1f)
                    .padding(6.dp)

            ) {
                Row(modifier = Modifier.align(Alignment.Center)){
                    Text(
                        text = sum1,
                        modifier = Modifier,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        fontSize = 16.sp, color = black
                    )
                    if (boolean){
                        Text(
                            text = stringResource(id = R.string.som),
                            modifier = Modifier.padding(start = 4.dp),
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            fontSize = 16.sp, color = textColor
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .shadow(1.dp, CircleShape)
                    .background(white)
                    .padding(6.dp)

            ) {
                Row(modifier = Modifier.align(Alignment.Center)){
                    Text(
                        text = sum2,
                        modifier = Modifier,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        fontSize = 16.sp, color = primaryColor
                    )
                  if (boolean)  {
                        Text(
                            text = stringResource(id = R.string.som),
                            modifier = Modifier.padding(start = 4.dp),
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            fontSize = 16.sp, color = btnInvisibleColor
                        )
                    }
                }
            }
        }
    }
}