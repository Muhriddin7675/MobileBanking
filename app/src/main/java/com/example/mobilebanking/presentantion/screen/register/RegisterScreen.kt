package com.example.mobilebanking.presentantion.screen.register

import RegisterScreenContract
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.mobilebanking.R
import com.example.mobilebanking.presentantion.dialog.RuUzBottomDialog
import com.example.mobilebanking.ui.componnent.ButtonComponent
import com.example.mobilebanking.ui.componnent.PhoneInputComponent
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.disabledColors
import com.example.mobilebanking.ui.theme.iconColorPaleBlack
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColorBlue
import com.example.mobilebanking.ui.theme.textInputColor
import com.example.mobilebanking.util.PHONE_NUMBER_LENGTH
import com.example.mobilebanking.util.myLog
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.orbitmvi.orbit.compose.collectState

class RegisterScreen : Screen {
    @Composable
    override fun Content() {

        val model: RegisterScreenContract.RegisterScreenModel = getViewModel<RegisterScreenModel>()

        val uiSate = model.collectAsState().value
        val context = LocalContext.current

        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        var languageState = true



        model.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is RegisterScreenContract.SideEffect.OpenLanguageDialog -> {
                    bottomSheetNavigator.show(
                        RuUzBottomDialog(
                            onClickButton = {
                                myLog("$it language st set ")
                                RegisterScreenContract.Intent.SetLanguageState(it)
                                RegisterScreenContract.Intent.SetUiLanguageState
                                bottomSheetNavigator.hide()
                            },
                            bool = sideEffect.ruInUz,
                            clickCancel = { bottomSheetNavigator.hide() }
                        )
                    )
                }

                else -> {}
            }
        }
        model.collectState { uiState ->
            when (uiState) {
                is RegisterScreenContract.UiState.LanguageState -> {
                    myLog("${uiState.bool} collectState language st set ")
                    languageState = uiState.bool
                }
            }
        }

        RegisterContent(model::onEventDispatcher, model.collectAsState().value)


    }
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun RegisterContent(
    onEventDispatcher: (RegisterScreenContract.Intent) -> Unit = {},
    uiState:RegisterScreenContract.UiState
    ) {
    var languageState by remember { mutableStateOf(true) }
    var buttonSt by remember { mutableStateOf(false) }

    when(uiState){
        is RegisterScreenContract.UiState.LanguageState -> {
            myLog("${uiState.bool} collectState language st set ")
            languageState = uiState.bool
        }
    }
    onEventDispatcher.invoke(RegisterScreenContract.Intent.SetUiLanguageState)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp, top = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(0.dp)
                    .background(textInputColor)
                    .padding(10.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(), enabled = true, onClick = {
                            onEventDispatcher.invoke(RegisterScreenContract.Intent.OpenLanguageDialog)
                        }, indication = null, onClickLabel = ""
                    )
            ) {
                onEventDispatcher.invoke(RegisterScreenContract.Intent.SetUiLanguageState)

                if (languageState) {
                    Text(
                        text = stringResource(id = R.string.language_uz),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.uz),
                        contentDescription = "flag",
                        modifier = Modifier
                            .height(24.dp)
                            .width(36.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .align(Alignment.CenterVertically)
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.language_ru),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_russian),
                        contentDescription = "flag",
                        modifier = Modifier
                            .height(24.dp)
                            .width(36.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.image_crickle_green),
                contentDescription = "image",
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Raqamingizni kiriting",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Mijoz yoki kirish uchun",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Normal
        )

        var phoneSt by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        PhoneInputComponent(
            text = phoneSt,
            leadingIcon = {
                Text(
                    stringResource(id = R.string.phone_prefix),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    modifier = Modifier.padding(start = 16.dp)
                )
            },
            modifier = Modifier.padding(top = 32.dp),
            onTextChanged = {
                phoneSt = it
                if (it.length < PHONE_NUMBER_LENGTH) {
                    buttonSt = false
                } else {
                    buttonSt = true
                    focusManager.clearFocus()
                }
            },
            enabled = true,
            isError = false,
            error = ""
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                ButtonComponent(
                    text = "Davom etish",
                    onClicked = {
                        onEventDispatcher.invoke(
                            RegisterScreenContract.Intent.ClickNextButton(
                                phoneSt
                            )
                        )
                    },
                    enabledColor = primaryColor,
                    disabledColor = disabledColors,
                    enabled = buttonSt,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Davome etish tumgasini bosih orqali men",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 14.sp,
                    color = iconColorPaleBlack
                )
                Text(
                    text = "Xizmatlar ko'rsatish haqidaig oferta shartlarini",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = textColorBlue,
                    fontSize = 14.sp,

                    )
                Text(
                    text = "qabul qilaman va shaxsiy malumotlarni qayta",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 14.sp,
                    color = iconColorPaleBlack

                )
                Text(
                    text = "ishlashga rozilik bildiraman",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 14.sp,
                    color = iconColorPaleBlack

                )
                Spacer(modifier = Modifier.height(16.dp))


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginContent() {
    MobileBankingTheme {
        RegisterContent({},RegisterScreenContract.UiState.LanguageState(true))
    }

}


