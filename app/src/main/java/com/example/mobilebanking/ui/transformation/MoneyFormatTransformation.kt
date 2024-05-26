package com.example.mobilebanking.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MoneyFormatTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val out = StringBuilder()
        var count = 0

        // Insert spaces from the right for every three digits
        for (i in originalText.indices.reversed()) {
            if (originalText[i].isDigit()) {
                out.insert(0, originalText[i])
                count++
                if (count % 3 == 0 && i != 0) {
                    out.insert(0, ' ')
                }
            } else {
                out.insert(0, originalText[i])
            }
        }

        val transformedText = out.toString()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var extraSpaces = 0
                var digits = 0
                for (i in 0 until offset) {
                    if (originalText[i].isDigit()) {
                        digits++
                        if (digits % 3 == 0 && i != originalText.length - 1) {
                            extraSpaces++
                        }
                    }
                }
                return offset + extraSpaces
            }

            override fun transformedToOriginal(offset: Int): Int {
                var extraSpaces = 0
                var transOffset = offset
                while (transOffset > 0 && extraSpaces < offset) {
                    transOffset--
                    if (transformedText[transOffset] == ' ') {
                        extraSpaces++
                    }
                }
                return offset - extraSpaces
            }
        }

        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }
}
