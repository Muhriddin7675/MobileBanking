package com.example.mobilebanking.presentantion.screen.dataentry

import CardNumberTransformation
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
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
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textInputColor
import com.example.mobilebanking.ui.theme.white

class DataEntryScreen : Screen {
    @Composable
    override fun Content() {
        val model: DataEntryContract.Model = getViewModel<DataEntryModel>()

        MobileBankingTheme {
            DataEntryContent(model::onEventDispatcher)
        }

    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun DataEntryContent(onEventDispatcher: (DataEntryContract.Intent) -> Unit) {
        var pasportId by remember { mutableStateOf("") }
        var jshshr by remember { mutableStateOf("") }


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
                                onEventDispatcher.invoke(DataEntryContract.Intent.PopBackStack)
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
                    .background(white)

            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(top = 12.dp)
                )
                {

                    BasicTextField(
                        value = pasportId,
                        onValueChange = {
                            if (it.length <= 16) {
                                pasportId = it
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
                            .background(color = textInputColor, shape = RoundedCornerShape(12.dp))
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
                    if (!pasportId.isEmpty()) {
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
                                        pasportId = ""
                                    }),
                        )
                    }


                }
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = jshshr,
                    onValueChange = { jshshr = it },
                    label = { Text("Label") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation = VisualTransformation.None,
                    singleLine = false,

                )

            }


            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                        .background(white)
                        .align(Alignment.BottomCenter)
                ) {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(50.dp),
                        enabled = pasportId.length == 16 && jshshr.length == 5,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor,
                            contentColor = white,
                            disabledContainerColor = btnInvisibleColor,
                            disabledContentColor = white
                        ),
                        onClick = {

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

    @Preview
    @Composable
    fun DataEntryPreview() {
        MobileBankingTheme {
            DataEntryContent({})
        }
    }
}
