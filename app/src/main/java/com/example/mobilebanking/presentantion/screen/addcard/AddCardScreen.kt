package com.example.mobilebanking.presentantion.screen.addcard


import CardNumberTransformation
import CardScannerScreen
import android.annotation.SuppressLint
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
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
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.addCardEndColor
import com.example.mobilebanking.ui.theme.addCardStartColor
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.btnInvisibleColor
import com.example.mobilebanking.ui.theme.errorColorX
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.textInputColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.ui.transformation.CardYearTransformation
import com.example.mobilebanking.util.checkExpirationDateValidation
import com.example.mobilebanking.util.myLog

class AddCardScreen : Screen {

    @ExperimentalGetImage
    @Composable
    override fun Content() {
        val model: AddCardContract.Model = getViewModel<AddCardModule>()
        AddCardScreenContent(model::onEventDispatcher)

    }

    @ExperimentalGetImage
    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun AddCardScreenContent(onEventDispatcher: (AddCardContract.Intent) -> Unit) {
        val context = LocalContext.current
        var cardNumber by remember { mutableStateOf("") }
        var cardYear by remember { mutableStateOf("") }
        var showCamera by remember { mutableStateOf(false) }

        if (showCamera) {
            CardScannerScreen(setCardNumber = {
                cardNumber = it
            }, setCardYear = {
                cardYear = it.replace("/", "")
                showCamera = false
            })
            myLog("add card screen : $cardNumber   ///   $cardYear")

        } else {
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
                                    onEventDispatcher.invoke(AddCardContract.Intent.PopBackStack)
                                }),
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = stringResource(id = R.string.add_card),
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        color = black,
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(210.dp)
                        .shadow(
                            1.dp,
                            RoundedCornerShape(16.dp)
                        )
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(addCardStartColor, addCardEndColor),
                                start = Offset(1f, 1.2f),
                                end = Offset.Infinite
                            ), shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(top = 12.dp)
                    )
                    {

                        BasicTextField(
                            value = cardNumber,
                            onValueChange = {
                                if (it.length <= 16) {
                                    cardNumber = it
                                }
                            },
                            textStyle = TextStyle(
                                color = black,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular))
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = textInputColor,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(16.dp)
                                .padding(end = 42.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = CardNumberTransformation,
                            keyboardActions = KeyboardActions(
                                onDone = {
                                }
                            ),
                        )
                        if (!cardNumber.isEmpty()) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_cencel_cricl),
                                contentDescription = "search",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                                    .size(16.dp)
                                    .clickable(interactionSource = MutableInteractionSource(),
                                        indication = null,
                                        enabled = true,
                                        onClickLabel = null,
                                        onClick = {
                                            cardNumber = ""
                                        }),
                            )
                        } else {

                            Text(
                                text = stringResource(id = R.string.card_number),
                                color = textColor,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 16.dp)
                            )

                            Image(
                                painter = painterResource(id = R.drawable.ic_action_scan_card),
                                contentDescription = "search",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                                    .clickable {
                                        showCamera = true
                                    }
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(130.dp)
                            .padding(start = 12.dp, top = 12.dp)
                    )
                    {

                        BasicTextField(
                            value = cardYear,
                            onValueChange = {
                                if (cardYear.length < 4) {
                                    cardYear = it
                                }
                            },
                            textStyle =
                            if (!cardYear.checkExpirationDateValidation() && cardYear.length == 4) {
                                TextStyle(
                                    color = errorColorX,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
                                )
                            } else {
                                TextStyle(
                                    color = black,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
                                )

                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = textInputColor,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(16.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = CardYearTransformation,
                            keyboardActions = KeyboardActions(
                                onDone = {
                                }
                            ),
                        )
                        if (!cardYear.checkExpirationDateValidation() && cardYear.length == 4) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_assist),
                                contentDescription = "search",
                                colorFilter = ColorFilter.tint(errorColorX),
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                                    .size(16.dp)
                                    .clickable(interactionSource = MutableInteractionSource(),
                                        indication = null,
                                        enabled = true,
                                        onClickLabel = null,
                                        onClick = {
                                            cardYear = ""
                                        }),
                            )}
                        else if (cardYear.isNotEmpty()) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_cencel_cricl),
                                contentDescription = "search",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                                    .size(16.dp)
                                    .clickable(interactionSource = MutableInteractionSource(),
                                        indication = null,
                                        enabled = true,
                                        onClickLabel = null,
                                        onClick = {
                                            cardYear = ""
                                        }),
                            )
                        } else {

                            Text(
                                text = stringResource(id = R.string.ooYY),
                                color = textColor,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 16.dp)
                            )

                        }

                    }

                }

                if(!cardYear.checkExpirationDateValidation() && cardYear.length == 4){
                    Text(
                        text = stringResource(id = R.string.cardIsPeriodIncorrect),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = errorColorX,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 32.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
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
                        enabled = cardNumber.length == 16 && cardYear.length == 4,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor,
                            contentColor = white,
                            disabledContainerColor = btnInvisibleColor,
                            disabledContentColor = white
                        ),
                        onClick = {
                            onEventDispatcher(AddCardContract.Intent.AddCard(
                                cardNumber = cardNumber,
                                expiryYear = "20${cardYear.substring(2)}",
                                expiryMonth = cardYear.substring(0,2)
                            ))
                        }) {
                        Text(
                            text = stringResource(id = R.string.btn_continue),
                            fontFamily = FontFamily(Font(R.font.pnfont_medium))
                        )
                    }
                }
            }
        }

    }


    @ExperimentalGetImage
    @Preview
    @Composable
    fun AddCardPreview() {
        MobileBankingTheme {
            AddCardScreenContent {}
        }
    }
}



