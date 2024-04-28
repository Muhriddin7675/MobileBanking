package com.example.mobilebanking.screen.main

import android.widget.GridLayout.Spec
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.CardAvia
import com.example.mobilebanking.ui.CardMIB
import com.example.mobilebanking.ui.CardMyHouse
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.circleEndColorGreen
import com.example.mobilebanking.ui.theme.circleStartColorGreen
import com.example.mobilebanking.ui.theme.securityCardEndColor
import com.example.mobilebanking.ui.theme.securityCardStartColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white

class MainScreen : Screen {
    @Composable
    override fun Content() {

    }

    @Composable
    fun MainScreenContent() {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(appBackgroundColorWhite)
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
                    text = "+998 94 657 76 75", Modifier.align(Alignment.CenterVertically),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 20.sp
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
                        Image(
                            painter = painterResource(id = R.drawable.ic_cencel),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_cencel),
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
                        text = stringResource(id = R.string.mening_pulim), fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = textColor
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Text(
                            text = "0", fontSize = 28.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
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
                                painter = painterResource(id = R.drawable.ic_eye),
                                contentDescription = "eye",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .shadow(elevation = 5.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                            text = stringResource(id = R.string.paynet_carta),
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            color = Color.Black
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .padding(end = 12.dp, top = 12.dp)
                                    .align(Alignment.CenterEnd),
                                text = stringResource(id = R.string.bu_nima),
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
                                text = stringResource(id = R.string.paynet_carta),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                color = textColor
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "0", fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
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
                            text = R.string.toldirish
                        )
                        Card(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(4.dp)
                                .weight(1f)
                                .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white),
                            icon = R.drawable.ic_add,
                            text = R.string.otkazish
                        )
                        Card(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(4.dp)
                                .weight(1f)
                                .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white),
                            icon = R.drawable.ic_add,
                            text = R.string.tolash
                        )
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(elevation = 5.dp, RoundedCornerShape(16.dp))
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
                                text = "Sizning darajangiz:Boshlangich",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                color = textColor
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "0", fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(id = R.string.tanga),
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
                                    .background(appBackgroundColorWhite)
                                    .clip(
                                        CircleShape
                                    ),
                                progress = 0f,
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
                            text = "Ayirboshlash",
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
                        .shadow(elevation = 5.dp, RoundedCornerShape(16.dp))
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
                                text = stringResource(id = R.string.paynet_xaf),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                            )
                            Text(
                                text = stringResource(id = R.string.xaf_oshi),
                                modifier = Modifier.padding(start = 12.dp),
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular))
                            )
                            Text(
                                text = stringResource(id = R.string.xoxlaysizmi),
                                modifier = Modifier.padding(start = 12.dp),
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular))
                            )
                            Button(
                                modifier = Modifier
                                    .padding(
                                        start = 12.dp,
                                        bottom = 12.dp,
                                        top = 8.dp
                                    )
                                    .height(36.dp),
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(white),

                                ) {
                                Text(
                                    text = stringResource(id = R.string.shaxingizni_tas),
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
                            .shadow(elevation = 5.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(white)
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                            text = stringResource(id = R.string.mening_kar),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white)
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
                                    text = stringResource(id = R.string.kart_qosh),
                                    color = Color.Black,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(
                                        Font(R.font.pnfont_regular)
                                    ),
                                )
                            }
                        }

                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(start = 6.dp)
                            .shadow(elevation = 5.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(color = white)
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                            text = stringResource(id = R.string.keshbek_his),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                        )
                        Text(
                            modifier = Modifier.padding(top = 12.dp, start = 8.dp),
                            text = stringResource(id = R.string.balans),
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                            color = textColor,
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = "0", fontSize = 18.sp,
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
                                        top = 8.dp,
                                        end = 8.dp,
                                        start = 8.dp
                                    )
                                    .height(24.dp)

                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    text = stringResource(R.string.bugun),
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
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
                                                start = 4.dp,
                                                end = 4.dp,
                                                top = 3.dp,
                                                bottom = 3.dp
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

                CardAvia()
                CardMyHouse()
                CardMIB()


            }

        }
    }

    @Preview
    @Composable
    fun PreviewMainScreen() {
        MobileBankingTheme {
            MainScreenContent()
        }

    }

    @Composable
    fun Card(
        modifier: Modifier,
        @DrawableRes icon: Int,
        @StringRes text: Int
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
}