package com.example.mobilebanking.ui.componnent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mobilebanking.R

@Composable
fun CustomTextView(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: Int = 400,
    fontSize: Int = 16,
    color:Color = Color.White
) {
    Text(
        text = text,
        modifier = modifier,
        fontWeight = FontWeight(fontWeight),
        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
        fontSize = fontSize.sp,
        color = color
    )
}