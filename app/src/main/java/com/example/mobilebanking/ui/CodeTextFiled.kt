
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.Typography
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.gray

@Composable
fun CodeTextField(
    value: String,
    length: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
        value = value,
        singleLine = true,
        onValueChange = {
            if (it.length <= length) {
                onValueChange(it)
            }
        },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            Box {
                // hide the inner text field as we are dwelling the text field ourselves
                CompositionLocalProvider(
                    LocalTextSelectionColors.provides(
                        TextSelectionColors(
                            Color.Transparent,
                            Color.Transparent
                        )
                    )
                ) {
                    Box(modifier = Modifier.drawWithContent { }) {
                        innerTextField()
                    }
                }
                Row(
                    Modifier.fillMaxWidth().align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {


                    repeat(length) { index ->
                        // if char is not null, show it, otherwise show empty box
                        val currentChar = value.getOrNull(index)
                        Box(
                            modifier = modifier
                                .size(width = 48.dp, height = 56.dp)
                                .clip( shape = RoundedCornerShape(16.dp))
                                .border(
                                    width = 1.dp,
                                    color = if (currentChar != null) Color.White else gray,
                                ) .background(gray)
                                .align(Alignment.CenterVertically),
                        ) {
                            if (currentChar != null) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = currentChar.toString(),
                                    textAlign = TextAlign.Center,
                                    style = Typography.bodyLarge.copy(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                                        fontWeight = FontWeight.Normal,
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
private fun SearchComponentPreview() {
    MobileBankingTheme {
        var phoneSt by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        CodeTextField(
            value = phoneSt,
            length = 6,
            onValueChange = {
//                phoneSt = it
//                if (it.length < 6) {
//                    buttonSt = false
//                } else {
//                    buttonSt = true
//                    focusManager.clearFocus()
//                }
            },
            modifier = Modifier.padding(),
        )
    }
}