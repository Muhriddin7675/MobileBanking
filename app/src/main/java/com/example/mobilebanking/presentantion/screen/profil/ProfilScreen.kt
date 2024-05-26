package com.example.mobilebanking.presentantion.screen.profil

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.mobilebanking.R
import com.example.mobilebanking.presentantion.dialog.AppRateBottomDialog
import com.example.mobilebanking.presentantion.dialog.LogOutDialog
import com.example.mobilebanking.presentantion.dialog.PaymentInfoBottomDialog
import com.example.mobilebanking.presentantion.dialog.ReferenceBottomDialog
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.errorColorX
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.formatPhoneNumber
import com.example.mobilebanking.util.makePhoneCall
import com.example.mobilebanking.util.openEmailIntent
import com.example.mobilebanking.util.openWebsite
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class ProfilScreen : Screen {
    @Composable
    override fun Content() {
        val model: ProfilContract.Model = getViewModel<ProfilModel>()
        MobileBankingTheme {
            val uiState = model.collectAsState().value
            ProfilContent(uiState = uiState, onEventDispatcher = model::onEventDispatcher)

            val bottomSheetNavigator = LocalBottomSheetNavigator.current


            model.collectSideEffect { sideEffect ->
                when (sideEffect) {
                    ProfilContract.SideEffect.OpenPaymentAbout -> {
                        bottomSheetNavigator.show(
                            PaymentInfoBottomDialog(clickCancel = { bottomSheetNavigator.hide() })
                        )
                    }

                    ProfilContract.SideEffect.OpenReference -> {
                        bottomSheetNavigator.show(
                            ReferenceBottomDialog(
                                clickCancel = {
                                    bottomSheetNavigator.hide()
                                }
                            )
                        )
                    }

                    ProfilContract.SideEffect.OpenRite -> {
                        bottomSheetNavigator.show(
                            AppRateBottomDialog(
                                clickBad = { bottomSheetNavigator.hide() },
                                clickGood = { bottomSheetNavigator.hide() })
                        )
                    }

                    ProfilContract.SideEffect.OpenLogAut -> {
                        bottomSheetNavigator.show(
                            LogOutDialog(
                                logOut = {
                                    bottomSheetNavigator.hide()
                                    ProfilContract.Intent.LogOut
                                },
                                dismiss = {
                                    bottomSheetNavigator.hide()
                                }
                            )
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    fun ProfilContent(
        uiState: ProfilContract.UIState,
        onEventDispatcher: (ProfilContract.Intent) -> Unit
    ) {
        var phoneNumber by remember { mutableStateOf("+998 90 657 76 75") }

        onEventDispatcher.invoke(ProfilContract.Intent.InitUiState)
        when (uiState) {
            is ProfilContract.UIState.InitState -> {
                phoneNumber = uiState.phoneNumber.formatPhoneNumber()
            }
        }
        val context = LocalContext.current
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
                                onEventDispatcher.invoke(ProfilContract.Intent.PopBackStack)
                            }),

                    )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.profil),
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    color = black,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .shadow(0.4.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    Text(
                        text = phoneNumber,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .padding(vertical = 4.dp)
                            .padding(horizontal = 16.dp)
                            .shadow(1.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(white)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.sad_emoji),
                            contentDescription = "support",
                            modifier = Modifier
                                .padding(start = 16.dp, end = 12.dp)
                                .align(Alignment.CenterVertically)
                                .size(36.dp),
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.your_situation),
                                modifier = Modifier,
                                fontSize = 16.sp,
                                color = textColor,
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                            )
                            Text(
                                text = stringResource(id = R.string.anonymous),
                                fontSize = 18.sp,
                                color = black,
                                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            )
                        }
                    }
                    Button(
                        onClick = {
                                  onEventDispatcher.invoke(ProfilContract.Intent.OpenIdentityVerificationScreen)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor,
                            contentColor = white
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.buttonVerifyIdentity),
                            fontFamily = FontFamily(Font(R.font.pnfont_medium))
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .shadow(0.4.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    Text(
                        text = stringResource(id = R.string.support),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        modifier = Modifier.padding(start = 16.dp, top = 12.dp)
                    )
                    CartTextComponent(
                        icon = R.drawable.ic_operations_comment,
                        text = stringResource(id = R.string.chat_Assistant),
                        onClick = {
                            openWebsite(context, "https://chat.paynet.uz")
                        })
                    CartTextComponent(
                        icon = R.drawable.ic_action_phone_alt,
                        text = stringResource(id = R.string.toCall),
                        onClick = {
                            makePhoneCall(context, "+998712020707")
                        })
                    CartTextComponent(
                        icon = R.drawable.ic_action_mail,
                        text = stringResource(id = R.string.emailWrite),
                        onClick = {
                            openEmailIntent(context, "support@paynet.uz", "", "")
                        })

                }
                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .shadow(0.4.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    Text(
                        text = stringResource(id = R.string.usefulInformation),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        modifier = Modifier.padding(start = 16.dp, top = 12.dp)
                    )
                    CartTextComponent(
                        icon = R.drawable.ic_assist,
                        text = stringResource(id = R.string.paynetAbaut),
                        onClick = {
                            onEventDispatcher.invoke(ProfilContract.Intent.OpenPaymentAbout)
                        })
                    CartTextComponent(
                        icon = R.drawable.ic_question,
                        text = stringResource(id = R.string.reference),
                        onClick = {

                            onEventDispatcher.invoke(ProfilContract.Intent.OpenReference)

                        })

                    CartTextComponent(
                        icon = R.drawable.ic_location,
                        text = stringResource(id = R.string.location),
                        onClick = {
                            onEventDispatcher.invoke(ProfilContract.Intent.OpenMapScreen)
                        })

                }
                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .shadow(0.4.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    CartTextComponent(
                        icon = R.drawable.ic_actions_settings,
                        text = stringResource(id = R.string.appSetting),
                        onClick = {
                            onEventDispatcher.invoke(ProfilContract.Intent.OpenSettingScreen)
                        })
                }
                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .shadow(0.4.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    CartTextComponent(
                        icon = R.drawable.ic_star,
                        text = stringResource(id = R.string.appRate),
                        onClick = {
                            onEventDispatcher.invoke(ProfilContract.Intent.OpenRite)
                        })
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .clickable(interactionSource = MutableInteractionSource(),
                            indication = null,
                            enabled = true,
                            onClickLabel = null,
                            onClick = {
                                onEventDispatcher.invoke(ProfilContract.Intent.OpenLogAut)
                            }),
                ) {
                    Row(modifier = Modifier.align(Alignment.Center)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_action_sign_in),
                            contentDescription = "sign in",
                            colorFilter = ColorFilter.tint(errorColorX)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = stringResource(id = R.string.logAutProfil),
                            color = errorColorX,
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            fontSize = 15.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun ProfilPreview() {
        MobileBankingTheme {
            ProfilContent(uiState = ProfilContract.UIState.InitState("")) {}
        }

    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CartTextComponent(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource(),
                enabled = true,
                onClickLabel = "",
                onClick = {
                    onClick.invoke()
                })
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "support",
            modifier = Modifier
                .padding(start = 16.dp, end = 12.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_regular))
        )
        Image(
            painter = painterResource(R.drawable.ic_right),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterVertically)
        )

    }
}


