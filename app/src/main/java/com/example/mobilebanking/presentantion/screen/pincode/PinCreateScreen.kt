package com.example.mobilebanking.presentantion.screen.pincode

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.CodeChecker
import com.example.mobilebanking.ui.componnent.CodeCreator
import com.example.mobilebanking.ui.componnent.text.PinCodeCircle
import com.example.mobilebanking.ui.componnent.text.TextBoldBlack
import com.example.mobilebanking.ui.componnent.text.TextNormalBlack
import com.example.mobilebanking.ui.theme.circleDefaultColor
import com.example.mobilebanking.ui.theme.pinScreenBgLight
import com.example.mobilebanking.ui.theme.textColor
import org.orbitmvi.orbit.compose.collectAsState

class PinCreateScreen : Screen {
    @Composable
    override fun Content() {
//        var phoneNumber = ""
        val model: PinCreateContract.PinCheckScreenModel = getViewModel<PinCreateModel>()

//        model.collectState { state ->
//            when (state) {
//                is PinCreateContract.UiState.PhoneNumber -> {
//                    phoneNumber = state.phone
//                }
//            }
//        }
        model.onEventDispatcher(PinCreateContract.Intent.SetPhoneNumber)

        PinCreateContent(model.collectAsState().value, model::onEventDispatcher)
    }
}

@Composable
fun PinCreateContent(
    uiState: PinCreateContract.UiState,
    onEventDispatcher: (PinCreateContract.Intent) -> Unit,

    ) {
    var pinCode = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("+998") }

    when (uiState) {
        is PinCreateContract.UiState.PhoneNumber -> {
            phoneNumber.value = uiState.phone
        }

        is PinCreateContract.UiState.Display -> {

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(pinScreenBgLight),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val context = LocalContext.current
        val PASSWORD_LENGTH = 4
        val listOfCircleColors =
            remember {
                mutableStateListOf<Color>().apply {
                    repeat(PASSWORD_LENGTH) {
                        this.add(circleDefaultColor)
                    }
                }
            }

        Spacer(modifier = Modifier.height(64.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_lock_svg),
            contentDescription = "lock icon",
            Modifier
                .padding(bottom = 16.dp)
                .size(60.dp)
        )

        TextBoldBlack(
            text = if (pinCode.value.isEmpty()) {
                stringResource(id = R.string.create_pin_code)
            } else stringResource(id = R.string.create_pin_code_reset),
            fontSize = 22.sp,
            letterSpacing = 0.8.sp
        )

        TextNormalBlack(
            text = stringResource(id = R.string.your_phone_number),
            fontSize = 12.sp,
            letterSpacing = 0.8.sp,
            color = textColor
        )

        TextNormalBlack(
            text = phoneNumber.value, fontSize = 12.sp, color = textColor, letterSpacing = 0.8.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(PASSWORD_LENGTH) {
                PinCodeCircle(color = listOfCircleColors[it], radius = 12, 12)
            }
        }
        Spacer(modifier = Modifier.weight(1f))


        if (pinCode.value.isEmpty()) {
            CodeCreator(modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
                onPasswordEntered = {
                        pinCode.value = it
                        onEventDispatcher.invoke(PinCreateContract.Intent.SetPinCode(pinCode.value))
                },
                listOfCircleColors = listOfCircleColors,
                onChangeCircleColor = {
                    it.forEachIndexed { index, color ->
                        listOfCircleColors[index] = listOfCircleColors[index].copy(
                            alpha = color.alpha,
                            blue = color.blue,
                            green = color.green,
                            red = color.red
                        )
                    }
                }
            )

        } else {
            CodeChecker(modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
                onCorrectPinCodeListener = {
//                    Toast.makeText(context, "CORRECT", Toast.LENGTH_SHORT).show()
                    onEventDispatcher.invoke(PinCreateContract.Intent.ClickNextButton)
                },
                onIncorrectPinCodeListener = {
//                    Toast.makeText(context, "INCORRECT", Toast.LENGTH_SHORT).show()
                },
                correctUserPin = pinCode.value,
                listOfCircleColors = listOfCircleColors,
                onChangeCircleColor = {
                    it.forEachIndexed { index, color ->
                        listOfCircleColors[index] = listOfCircleColors[index].copy(
                            alpha = color.alpha, blue = color.blue, green = color.green, red = color.red
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun CreatePinPreview() {
    PinCreateContent(PinCreateContract.UiState.PhoneNumber("+998")) {

    }
}