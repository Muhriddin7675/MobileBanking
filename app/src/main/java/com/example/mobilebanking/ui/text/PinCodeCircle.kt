package uz.gita.mobilebanking.ui.components.custom_writers

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun PinCodeCircle(
    color: Color,
    radius: Int,
    padding: Int,
) {
    Canvas(modifier = Modifier
        .padding(padding.dp)
        .size(radius.dp),
        onDraw = {
            drawCircle(
                color = color, radius = radius.toFloat()
            )
        }
    )
}