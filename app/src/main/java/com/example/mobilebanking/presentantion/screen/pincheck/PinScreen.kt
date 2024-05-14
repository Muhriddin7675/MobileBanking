package com.example.mobilebanking.presentantion.screen.pincheck

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.mobilebanking.ui.componnent.CodeWriter
import com.example.mobilebanking.ui.componnent.PinCodeCircle
import com.example.mobilebanking.ui.text.TextBoldBlack
import com.example.mobilebanking.ui.text.TextNormal
import com.example.mobilebanking.ui.text.TextNormalBlack
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.circleDefaultColor
import com.example.mobilebanking.ui.theme.errorColor
import com.example.mobilebanking.ui.theme.pinScreenBgLight
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.util.hidePartOfNumber
import com.example.mobilebanking.util.requireBiometricAuth
import com.example.mobilebanking.util.vibrate
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.mobilebanking.presentation.pin.PinContract


class PinScreen : Screen {
    @Composable
    override fun Content() {
        MobileBankingTheme {
            val viewModel: PinContract.Model = getViewModel<PinModel>()
            PinContent(
                viewModel.collectAsState().value,
                viewModel::onEventDispatcher
            )
        }
    }
}

@Composable
fun PinContent(
    uiState: PinContract.UIState,
    onEventDispatcher: (PinContract.Intent) -> Unit
) {
    val context = LocalContext.current
    val ATTEMPTS_COUNT = 3
    val PASSWORD_LENGTH = 4
    var attemptsCount by remember { mutableIntStateOf(ATTEMPTS_COUNT) }
    var isErrorTextVisible by remember { mutableStateOf(false) }
    val listOfCircleColors =
        remember {
            mutableStateListOf<Color>().apply {
                repeat(PASSWORD_LENGTH) { this.add(circleDefaultColor) }
            }
        }

    var isClickNumbersEnabled by remember { mutableStateOf(true) }
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(attemptsCount) {
        if (attemptsCount != ATTEMPTS_COUNT) {
            context.vibrate(500)
            repeat(2) {
                shakeOffset.animateTo(
                    targetValue = 10f,
                    animationSpec = tween(
                        durationMillis = 5,
                        easing = LinearOutSlowInEasing
                    )
                )
                shakeOffset.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 5,
                        easing = LinearOutSlowInEasing
                    )
                )
            }
            changeColor(circleDefaultColor, listOfCircleColors)
            isClickNumbersEnabled = true
        }
    }

    LaunchedEffect(Unit){
        context.requireBiometricAuth(
            onSuccess = {
                onEventDispatcher(PinContract.Intent.ToMainScreen)
            },
            onError = { _, errorString ->
                Toast.makeText(
                    context,
                    errorString.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            },
            onFailed = {
                Toast.makeText(
                    context,
                    "Verification error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    // ui started here
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pinScreenBgLight),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(pinScreenBgLight),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(64.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_lock_svg),
                contentDescription = "lock icon",
                Modifier
                    .padding(bottom = 18.dp)
                    .size(60.dp)
            )

            TextBoldBlack(
                text = stringResource(id = R.string.enter_the_pin_code),
                fontSize = 22.sp,
                letterSpacing = 0.8.sp
            )

            TextNormalBlack(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(id = R.string.your_phone_number),
                fontSize = 14.sp,
                letterSpacing = 0.8.sp,
                color = textColor
            )

            TextBoldBlack(
                modifier = Modifier.padding(top = 4.dp),
                text = uiState.phoneNumber.hidePartOfNumber(),
                fontSize = 14.sp,
                color = textColor,
                letterSpacing = 0.8.sp
            )

            if (isErrorTextVisible) {
                TextNormal(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(id = R.string.incorrect_pin_code),
                    color = errorColor,
                )
                TextNormal(
                    text = "$attemptsCount " + stringResource(id = R.string.count_attempt_left),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            CodeWriter(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 32.dp),
                correctUserPin = uiState.pinCode,
                forgetPinCodeText = R.string.forget_pin_code,
                clearIcon = R.drawable.ic_keyboard_remove,
                fingerIcon = R.drawable.ic_operations_fingerprint,
                onPinCodeForgetClick = {

                },
                correctPinCodeListener = {
                    onEventDispatcher(PinContract.Intent.ToMainScreen)
                },
                incorrectPinCodeListener = {
                    changeColor(errorColor, listOfCircleColors)
                    attemptsCount--
                    isClickNumbersEnabled = false
                    isErrorTextVisible = true
                },
                onFingerButtonClick = {
                    context.requireBiometricAuth(
                        onSuccess = {
                            onEventDispatcher(PinContract.Intent.ToMainScreen)
                        },
                        onError = { _, errorString ->
                            Toast.makeText(
                                context,
                                errorString.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onFailed = {
                            Toast.makeText(
                                context,
                                "Verification error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
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
                },
                isClickNumbersEnabled = isClickNumbersEnabled
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = shakeOffset.value.dp),

            ) {
            repeat(PASSWORD_LENGTH) {
                PinCodeCircle(
                    color = listOfCircleColors[it],
                    radius = 10,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PinPreview() {
    PinContent(
        uiState = PinContract.UIState(
            "1234",
            "903553620"
        ),
        onEventDispatcher = {}
    )
}

private fun changeColor(color: Color, listOfCircleColors: MutableList<Color>) {
    listOfCircleColors.forEachIndexed { index, _ ->
        listOfCircleColors[index] = listOfCircleColors[index].copy(
            alpha = color.alpha,
            blue = color.blue,
            green = color.green,
            red = color.red
        )
    }
}