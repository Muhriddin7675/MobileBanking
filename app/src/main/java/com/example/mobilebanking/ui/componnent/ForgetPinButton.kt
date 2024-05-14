package com.example.mobilebanking.ui.componnent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForgetPinButton(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String,
    fontSize: Int,
    shape: CornerBasedShape = RoundedCornerShape(900.dp),
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .clip(shape)
            .background(
                color = Color.Transparent,
                shape = shape
            )
            .clickable(onClick = {
                onClick()
            }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = text,
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center
        )
    }
}