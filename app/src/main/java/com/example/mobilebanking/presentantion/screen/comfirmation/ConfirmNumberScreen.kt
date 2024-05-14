package com.example.mobilebanking.presentantion.screen.comfirmation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.ButtonComponent
import com.example.mobilebanking.ui.componnent.CodeTextField
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.disabledColors
import com.example.mobilebanking.ui.theme.primaryColor
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class ConfirmNumberScreen : Screen {
    @Composable
    override fun Content() {
        val model: ConfirmationContract.ConfirmationModel = getViewModel<ConfirmationModel>()

        val uiSate = model.collectAsState().value
        val context = LocalContext.current

        model.collectSideEffect {
//            when (it) {
//                is ConfirmationContract.SideEffect.ShowToast -> {
//                    Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
//                }
//            }
        }
        ConfirmPhoneContent(uiState = uiSate, model::onEventDispatcher)
    }
}

@Composable
private fun ConfirmPhoneContent(
    uiState: ConfirmationContract.UiState,
    onEventDispatcher: (ConfirmationContract.Intent) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Image(
            painterResource(id = R.drawable.ic_chewron_down),
            contentDescription = "Back",
            Modifier
                .size(width = 56.dp, height = 56.dp)
                .padding(12.dp)
                .clickable {
                    onEventDispatcher.invoke(ConfirmationContract.Intent.ClickBackButton)
                },
        )

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "MusicDisk",
            modifier = Modifier
                .padding(4.dp)
                .width(64.dp)
                .height(64.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(id = R.string.enter_sms_code),
            fontFamily = FontFamily(
                Font(R.font.pnfont_semibold)
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Black,
            fontSize = TextUnit(22f, TextUnitType.Sp),
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Medium,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = R.string.register_phone_text_bottom),
            fontFamily = FontFamily(
                Font(R.font.pnfont_semibold)
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0XFF988E8E),
            fontSize = TextUnit(14f, TextUnitType.Sp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        var codeSt by remember { mutableStateOf("") }
        var buttonSt by remember { mutableStateOf(false) }
        val focusManager = LocalFocusManager.current
        Spacer(modifier = Modifier.height(16.dp))
        CodeTextField(
            value = codeSt,
            length = 6,
            onValueChange = {
                codeSt = it
                if (it.length < 6) {
                    buttonSt = false
                } else {
                    buttonSt = true
                    focusManager.clearFocus()
                    onEventDispatcher.invoke(ConfirmationContract.Intent.ClickButton(codeSt))
                }
            },
            modifier = Modifier
                .padding()
                .align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.weight(1f))
        ButtonComponent(
            text = "Davom etish",
            onClicked = {
                onEventDispatcher.invoke(ConfirmationContract.Intent.ClickButton(codeSt))
            },
            enabledColor = primaryColor,
            disabledColor = disabledColors,
            enabled = buttonSt,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.padding(8.dp))

    }
}

@Preview
@Composable
private fun RegisterPreview() {
    MobileBankingTheme {
        ConfirmPhoneContent(ConfirmationContract.UiState.Display(""), {})
    }
}
