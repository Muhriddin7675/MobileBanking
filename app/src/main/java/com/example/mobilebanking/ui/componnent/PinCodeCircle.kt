package com.example.mobilebanking.ui.componnent

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun PinCodeCircle(
    color: Color,
    radius: Int,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .size(radius.dp),
        onDraw = {
            drawCircle(
                color = color,
                radius = radius.toFloat()
            )
        }
    )
}