package com.example.mobilebanking.ui.componnent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.disableContentColor
import com.example.mobilebanking.ui.theme.disabledColors
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.white

@Composable
fun ButtonComponent(
    text: String,
    onClicked: (() -> Unit),
    modifier: Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = false,
    enabledColor: Color = primaryColor,
    disabledColor: Color = disabledColors
) {

    Button(
        onClick = onClicked,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = enabledColor,
            disabledContainerColor = disabledColor,
            disabledContentColor = disableContentColor,
            contentColor = white

        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            Row {
                Text(text, fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.pnfont_semibold)))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonComponentPreview() {
    MobileBankingTheme {
        ButtonComponent(
            text = "Save",
            onClicked = { },
            isLoading = false,
            enabled = false,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

