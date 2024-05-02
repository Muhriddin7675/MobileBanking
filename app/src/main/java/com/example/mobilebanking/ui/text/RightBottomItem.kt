package com.example.mobilebanking.ui.text

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun RightBottomItem(
    modifier: Modifier,
    onClick: () -> Unit,
    @DrawableRes iconID: Int,
    shape: CornerBasedShape = CircleShape,

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
        Icon(
            painter = painterResource(id = iconID),
            contentDescription = "finger icon",
            modifier = Modifier.size(24.dp)
        )
    }
}