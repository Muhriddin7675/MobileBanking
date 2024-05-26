package com.example.mobilebanking.ui.componnent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.text.PinCodeNumberItem
import com.example.mobilebanking.ui.componnent.text.RightBottomItem
import com.example.mobilebanking.ui.theme.errorColor
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColor

@Composable
fun CodeChecker(
    modifier: Modifier,
    correctUserPin: String,
    circleCheckedColor: Color = primaryColor,
    circleDefaultColor: Color = textColor,
    circleErrorColor: Color = errorColor,
    listOfCircleColors: MutableList<Color>,
    onCorrectPinCodeListener: () -> Unit,
    onIncorrectPinCodeListener: () -> Unit,
    onChangeCircleColor: (MutableList<Color>) -> Unit
) {
    var currentPos by remember { mutableIntStateOf(-1) }
    val currentPassword by remember { mutableStateOf(StringBuilder()) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (i in 0..2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (j in 1..3) {
                    PinCodeNumberItem(
                        modifier = Modifier,
                        number = "${i * 3 + j}",
                        onClick = { number ->
                            try {
                                if (currentPos < correctUserPin.length - 1) {
                                    currentPassword.append(number)
                                    listOfCircleColors[++currentPos] = circleCheckedColor // increasing position
                                    onChangeCircleColor(listOfCircleColors)

                                    if (currentPos == correctUserPin.length - 1) {
                                        if (currentPassword.toString() == correctUserPin) {
                                            onCorrectPinCodeListener()
                                        } else {
                                            onIncorrectPinCodeListener()
                                            for (i in 0 until listOfCircleColors.size) {
                                                listOfCircleColors[i] = errorColor
                                            }

                                            onChangeCircleColor(listOfCircleColors)
                                        }
                                    }
                                }
                            } catch (e: Exception) {
                            }
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(modifier = Modifier.size(64.dp))

            PinCodeNumberItem(
                modifier = Modifier,
                number = "0",
                onClick = { number ->
                    try {
                        if (currentPos < correctUserPin.length - 1) {
                            currentPassword.append(number)
                            listOfCircleColors[++currentPos] = circleCheckedColor // increasing position
                            onChangeCircleColor(listOfCircleColors)

                            if (currentPos == correctUserPin.length - 1) {
                                if (currentPassword.toString() == correctUserPin) {
                                    onCorrectPinCodeListener()
                                } else {
                                    onIncorrectPinCodeListener()
                                }
                            }
                        }
                    } catch (e: Exception) {}
                }
            )

            if (currentPos != -1) {
                RightBottomItem(
                    modifier = Modifier,
                    onClick = {
                        try {
                            currentPassword.setLength(currentPassword.length - 1)
                            listOfCircleColors[currentPos--] = circleDefaultColor
                            onChangeCircleColor(listOfCircleColors)
                        } catch (e: Exception) {}
                    },
                    iconID = R.drawable.ic_keyboard_remove
                )
            } else {
                Box(modifier = Modifier.size(64.dp))
            }
        }
    }
}