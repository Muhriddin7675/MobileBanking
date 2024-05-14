package com.example.mobilebanking.presentantion.screen.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.example.mobilebanking.presentantion.screen.profil.CartTextComponent
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.gray
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.securityCardStartColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white
import org.orbitmvi.orbit.compose.collectAsState

class AppSettingScreen : Screen {
    @Composable
    override fun Content() {
        val model: AppSettingContract.Model = getViewModel<AppSettingModule>()

        MobileBankingTheme {
            AppSettingContent(model.collectAsState().value, model::onEventDispatcher)
        }
    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun AppSettingContent(
        uiState: AppSettingContract.UIState,
        onEventDispatcher: (AppSettingContract.Intent) -> Unit
    ) {
        var languageSt by remember { mutableStateOf(true) }
        var touchIdSwitchSt by remember { mutableStateOf(false) }
        var textServicesSt by remember { mutableStateOf("11.05.2024, 11:37:35") }
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
                                onEventDispatcher.invoke(AppSettingContract.Intent.PopBackStack)
                            }),

                    )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.appSetting),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    color = black,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp))
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(white)
            ) {
                Text(
                    text = stringResource(id = R.string.appLanguage),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(136.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = if (languageSt) {
                            Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(bottom = 16.dp, top = 8.dp)
                                .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp))
                                .clip(shape = RoundedCornerShape(16.dp))
                                .border(
                                    width = 1.2.dp,
                                    color = primaryColor,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .background(white)
                        } else {
                            Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(bottom = 16.dp, top = 8.dp)
                                .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp))
                                .clip(shape = RoundedCornerShape(16.dp))
                                .background(white)
                                .clickable(
                                    indication = null,
                                    interactionSource = MutableInteractionSource(),
                                    enabled = true,
                                    onClickLabel = "",
                                    onClick = {
                                        languageSt = true
                                    })
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.uz),
                            contentDescription = "uz",
                            modifier = Modifier.padding(12.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = stringResource(id = R.string.uzbek),
                            modifier = Modifier.padding(12.dp),
                            fontFamily = FontFamily(Font(R.font.pnfont_regular))
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = if (!languageSt) {
                            Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(bottom = 16.dp, top = 8.dp)
                                .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp))
                                .clip(shape = RoundedCornerShape(16.dp))
                                .border(
                                    width = 1.2.dp,
                                    color = primaryColor,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .background(white)
                        } else {
                            Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(bottom = 16.dp, top = 8.dp)
                                .shadow(elevation = 1.dp, shape = RoundedCornerShape(16.dp))
                                .clip(shape = RoundedCornerShape(16.dp))
                                .background(white)
                                .clickable(
                                    indication = null,
                                    interactionSource = MutableInteractionSource(),
                                    enabled = true,
                                    onClickLabel = "",
                                    onClick = {
                                        languageSt = false
                                    })
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ru),
                            contentDescription = "uz",
                            modifier = Modifier.padding(12.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = stringResource(id = R.string.russia),
                            modifier = Modifier.padding(12.dp),
                            fontFamily = FontFamily(Font(R.font.pnfont_regular))
                        )
                    }
                }
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
                    text = stringResource(id = R.string.safety),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp)
                )
                CartTextComponent(
                    icon = R.drawable.ic_lock,
                    text = stringResource(id = R.string.changePinCode),
                    onClick = {
                    })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_operations_fingerprint),
                        contentDescription = "support",
                        modifier = Modifier
                            .padding(start = 16.dp, end = 12.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = stringResource(id = R.string.FaceId),
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                            .padding(end = 24.dp),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular))
                    )

                    Switch(
                        checked = touchIdSwitchSt,
                        onCheckedChange = {
                            touchIdSwitchSt = it
                        },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = primaryColor,
                            checkedTrackColor = securityCardStartColor,
                            uncheckedTrackColor = gray,
                            uncheckedBorderColor = white,
                            uncheckedThumbColor = white
                        ),
                        interactionSource = MutableInteractionSource()
                    )

                }
                CartTextComponent(
                    icon = R.drawable.ic_smart_phone,
                    text = stringResource(id = R.string.devices),
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
                    text = stringResource(id = R.string.addition),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp)
                )


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(top = 4.dp, bottom = 4.dp)
                            .clickable(
                                indication = null,
                                interactionSource = MutableInteractionSource(),
                                enabled = true,
                                onClickLabel = "",
                                onClick = {})
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_refresh),
                            contentDescription = "support",
                            modifier = Modifier
                                .padding(start = 16.dp, end = 12.dp)
                                .align(Alignment.CenterVertically)
                                .size(28.dp)
                        )

                        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                            Text(
                                text = stringResource(id = R.string.services_directory),
                                modifier = Modifier,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pnfont_regular))
                            )
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = stringResource(id = R.string.updated),
                                    modifier = Modifier,
                                    fontSize = 12.sp,
                                    color = textColor,
                                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
                                )

                                Text(
                                    text = textServicesSt,
                                    modifier = Modifier.padding(start = 4.dp),
                                    fontSize = 12.sp,
                                    color = textColor,
                                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
                                )
                            }
                        }

                    }
            }

        }
    }


    @Preview
    @Composable
    fun AppSettingPreview() {
        MobileBankingTheme {
            AppSettingContent(AppSettingContract.UIState.InitState, {})
        }

    }

}
