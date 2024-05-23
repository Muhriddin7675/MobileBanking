package com.example.mobilebanking.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CardYearTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text)
    }

    private fun maskFilter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 9) text.text.substring(0..8) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (listOf(1, 4, 6).contains(i)) out += "/"
        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 5) return offset + 1
                if (offset <= 7) return offset + 2
                if (offset <= 9) return offset + 3
                return 12
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 7) return offset - 1
                if (offset <= 10) return offset - 2
                if (offset <= 12) return offset - 3
                return 9
            }
        }


        return TransformedText(AnnotatedString(out.toString()), numberOffsetTranslator)
    }
}
