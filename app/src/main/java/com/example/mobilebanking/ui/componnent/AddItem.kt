package com.example.mobilebanking.ui.componnent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.textInputColor

@Composable
fun AddItem(
    modifier: Modifier,
    text: String,
    icon: Int
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
                .clip(CircleShape)
                .background(textInputColor)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(36.dp)
            )
        }
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(0.3f),
            color = black,
            fontFamily = FontFamily(Font(R.font.pnfont_regular))
        )
    }
}