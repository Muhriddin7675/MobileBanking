import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CardNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Keep only digits
        val digits = text.text.filter { it.isDigit() }
        val stringBuilder = StringBuilder()

        // Loop through the digits and insert spaces after every 4th digit
        digits.forEachIndexed { index, c ->
            stringBuilder.append(c)
            // Add space after every 4 digits, but not at the end
            if (index % 4 == 3 && index != digits.lastIndex) {
                stringBuilder.append(' ')
            }
        }

        val newText = stringBuilder.toString()

        // Create an offset mapping to correlate original text indexing with transformed text
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > digits.length) return newText.length

                val spacesBeforeOffset = (offset - 1) / 4
                return offset + spacesBeforeOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > newText.length) return digits.length

                val spacesBeforeOffset = (offset - 1) / 5
                return offset - spacesBeforeOffset
            }
        }

        return TransformedText(AnnotatedString(newText), offsetMapping)
    }
}
