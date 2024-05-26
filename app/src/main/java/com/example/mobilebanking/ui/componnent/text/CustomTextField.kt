package com.example.mobilebanking.ui.componnent.text

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.mobilebanking.R

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    fontSize: Int,
    fontWeight: Int,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    cursorBrush: Brush = SolidColor(Color.Black),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation,
    color: Color = Color.Black
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        cursorBrush = cursorBrush,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        textStyle = TextStyle(
            fontSize = fontSize.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_regular)),
            fontWeight = FontWeight(fontWeight),
            color = color
        )
    )
}