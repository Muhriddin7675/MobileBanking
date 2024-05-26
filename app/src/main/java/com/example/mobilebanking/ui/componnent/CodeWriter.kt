package com.example.mobilebanking.ui.componnent

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobilebanking.ui.componnent.text.PinCodeNumberItem
import com.example.mobilebanking.ui.componnent.text.RightBottomItem
import com.example.mobilebanking.ui.theme.errorColor
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColor

@Composable
fun CodeWriter(
    modifier: Modifier,
    correctUserPin: String,
    @StringRes forgetPinCodeText: Int,
    @DrawableRes clearIcon: Int,
    @DrawableRes fingerIcon: Int,
    onPinCodeForgetClick: () -> Unit,
    correctPinCodeListener: () -> Unit,
    incorrectPinCodeListener: () -> Unit,
    onFingerButtonClick: () -> Unit,
    circleCheckedColor: Color = primaryColor,
    circleDefaultColor: Color = textColor,
    circleErrorColor: Color = errorColor,
    listOfCircleColors: MutableList<Color>,
    onChangeCircleColor: (MutableList<Color>) -> Unit,
    isClickNumbersEnabled: Boolean
) {
    var currentPos by remember { mutableIntStateOf(-1) }
    val currentPassword by remember { mutableStateOf(StringBuilder()) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (i in 0..2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (j in 1..3) {
                    PinCodeNumberItem(
                        modifier = Modifier,
                        number = "${i * 3 + j}",
                        onClick = { number ->
                            if (isClickNumbersEnabled)
                                if (currentPos < correctUserPin.length - 1) {
                                    currentPassword.append(number)
                                    listOfCircleColors[++currentPos] = circleCheckedColor
                                    onChangeCircleColor(listOfCircleColors)

                                    if (currentPos == correctUserPin.length - 1) {
                                        if (correctUserPin == currentPassword.toString()) {
                                            correctPinCodeListener()
                                        } else {
                                            incorrectPinCodeListener()
                                            currentPassword.clear()
                                            currentPos = -1
                                        }
                                    }
                                }
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ForgetPinButton(
                modifier = Modifier, onClick = {
                    onPinCodeForgetClick()
                },
                text = stringResource(id = forgetPinCodeText),
                fontSize = 8
            )
            PinCodeNumberItem(
                modifier = Modifier,
                number = "0",
                onClick = { number ->
                    if (isClickNumbersEnabled)
                        if (currentPos < correctUserPin.length - 1) {
                            currentPassword.append(number)
                            listOfCircleColors[++currentPos] = circleCheckedColor
                            onChangeCircleColor(listOfCircleColors)

                            if (currentPos == correctUserPin.length - 1) {
                                if (correctUserPin == currentPassword.toString()) {
                                    correctPinCodeListener()
                                } else {
                                    incorrectPinCodeListener()
                                }
                            }
                        }
                }
            )

            if (currentPos == -1) {
                RightBottomItem(
                    modifier = Modifier,
                    onClick = {
                        onFingerButtonClick()
                    },
                    iconID = fingerIcon
                )
            } else {
                RightBottomItem(
                    modifier = Modifier,
                    onClick = {
                        currentPassword.setLength(currentPassword.length - 1)
                        listOfCircleColors[currentPos--] = circleDefaultColor
                        onChangeCircleColor(listOfCircleColors)
                    },
                    iconID = clearIcon
                )
            }
        }
    }
}
