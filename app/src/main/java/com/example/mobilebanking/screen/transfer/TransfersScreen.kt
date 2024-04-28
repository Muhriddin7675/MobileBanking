package com.example.mobilebanking.screen.transfer

import android.graphics.drawable.shapes.Shape
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.aviaCardColor
import com.example.mobilebanking.ui.theme.myCardColor
import com.example.mobilebanking.ui.theme.paymentCard
import com.example.mobilebanking.ui.theme.rosCardColor
import com.example.mobilebanking.ui.theme.textInputColor
import com.example.mobilebanking.ui.theme.uzCardColor
import com.example.mobilebanking.ui.theme.white

class TransfersScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            TransfersContent()
        }
    }

    @Composable
    fun TransfersContent() {
        val items = List(1) { index -> "Item $index" }
        val ls = listOf(
            TemplateData("Qo'shish", R.drawable.ic_add_black_cricl)
        )
//        var text by remember { mutableStateOf("") }

        Column(
            Modifier
                .fillMaxSize()
                .background(white)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.otkazmalar),
                    fontSize = 32.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = stringResource(id = R.string.bekor_qilish),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                )

            }
            Spacer(modifier = Modifier.height(18.dp))

            Box {
                BasicTextField(
                    value = "1111 2222 3333 4444",
                    onValueChange = { it },
                    textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = textInputColor, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                        }
                    )
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 12.dp)
                ) {
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 16.dp)
            ) {


                CardStartTextEndImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 6.dp)
                        .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(myCardColor),
                    text = R.string.ozimning_kartamga, image = R.drawable.self_transfer_to_card
                )

                CardStartTextEndImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 6.dp)
                        .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(paymentCard),
                    text = R.string.paynet_kart,
                    image = R.drawable.self_transfer_to_wallet
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.shablonglar),
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                fontSize = 20.sp
            )

            LazyRow {
                items(ls.size) {
                    Column(
                        modifier = Modifier
                            .height(130.dp)
                            .width(100.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clip(RoundedCornerShape(32.dp))
                                .background(textInputColor)
                        ) {
                            Image(
                                painter = painterResource(id = ls[it].icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(36.dp)
                            )
                        }
                        Text(
                            text = ls[it].text,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .weight(0.3f),
                            fontFamily = FontFamily(Font(R.font.pnfont_regular))
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.xalqaro_ot),
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                fontSize = 20.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(top = 16.dp)
            ) {
//                CardStartTextEndImage(
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .padding(end = 6.dp)
//                        .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
//                        .weight(1f)
//                        .clip(RoundedCornerShape(16.dp))
//                        .background(myCardColor),
//                    text = R.string.rossiya, image = R.drawable.self_transfer_to_card
//                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 6.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(rosCardColor)
                ) {
                    Text(
                        text = stringResource(R.string.rossiya),
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 12.dp
                        ),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        fontSize = 20.sp
                    )
                    Image(
                        painter = painterResource(R.drawable.uzb_to_ru),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(6.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 6.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(uzCardColor)
                ) {
                    Text(
                        text = stringResource(R.string.ozbekiston),
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 12.dp
                        ),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        fontSize = 20.sp
                    )
                    Image(
                        painter = painterResource(R.drawable.ru_to_uzb),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(6.dp)

                    )
                }

            }


        }
    }


    @Preview
    @Composable
    fun TransfersPreview() {
        MobileBankingTheme {
            TransfersContent()
        }
    }

    class TemplateData(val text: String, val icon: Int)

    @Composable
    fun CardStartTextEndImage(
        modifier: Modifier,
        @StringRes text: Int,
        @DrawableRes image: Int
    ) {
        Row(
            modifier = modifier
        ) {
            Text(
                text = stringResource(id = text),
                Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .padding(start = 12.dp),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_regular))

            )
            Image(
                painter = painterResource(id = image), contentDescription = "",
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxHeight()
            )
        }

    }
}
