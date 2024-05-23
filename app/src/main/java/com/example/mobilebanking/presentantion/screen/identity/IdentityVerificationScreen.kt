package com.example.mobilebanking.presentantion.screen.identity

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white

class IdentityVerificationScreen : Screen {
    @Composable
    override fun Content() {

        val model: IdentityVerificationContract.Model = getViewModel<IdentityVerificationModel>()
        MobileBankingTheme {
            IdentityContent(model::onEventDispatcher)
        }
    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun IdentityContent(onEventDispatcher: (IdentityVerificationContract.Intent) -> Unit) {

        var progressNumber by remember { mutableStateOf(1) }

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
                                onEventDispatcher.invoke(IdentityVerificationContract.Intent.PopBackStack)
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
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.align(Alignment.CenterVertically)) {

                    Text(
                        text = progressNumber.toString(),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = black,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "/3",
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = textColor,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .background(appBackgroundColorWhite),
                progress = progressNumber / 3f,
                color = primaryColor,
                trackColor = white,
            )

            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.identification_step_one),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 28.dp)
                        .fillMaxWidth()
                        .height(172.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.your_document),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    color = black,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = stringResource(id = R.string.scan),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    color = black,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.increase_these_limits),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = stringResource(id = R.string.required_for_unlocking),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.you_few_minutes),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.you_are_document_selfie),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = stringResource(id = R.string.will_be_needed),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .clickable(interactionSource = MutableInteractionSource(),
                            indication = null,
                            enabled = true,
                            onClickLabel = null,
                            onClick = {


                            }),
                ) {
                    Text(
                        text = stringResource(id = R.string.passport_scan),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(vertical = 12.dp)
                            .weight(1f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_right),
                        contentDescription = "",
                        Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .clickable(interactionSource = MutableInteractionSource(),
                            indication = null,
                            enabled = true,
                            onClickLabel = null,
                            onClick = {


                            }),
                ) {
                    Text(
                        text = stringResource(id = R.string.id_card_scan),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(vertical = 12.dp)
                            .weight(1f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_right),
                        contentDescription = "",
                        Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .clickable(interactionSource = MutableInteractionSource(),
                            indication = null,
                            enabled = true,
                            onClickLabel = null,
                            onClick = {
                                onEventDispatcher.invoke(IdentityVerificationContract.Intent.OpenDataEntryScreen)
                            }),
                ) {
                    Text(
                        text = stringResource(id = R.string.manual_data_entry),
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        color = black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(vertical = 12.dp)
                            .weight(1f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_right),
                        contentDescription = "",
                        Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }


            }

        }

    }

    @Preview
    @Composable
    fun IdentityPreview() {
        MobileBankingTheme {
            IdentityContent({})
        }
    }

}