package com.example.mobilebanking.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiryDateTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val out = StringBuilder()
        for (i in text.text.indices) {
            if (i == 2 && text.text.length >= 2) {
                out.append('/')
            }
            out.append(text.text[i])
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = when {
                offset <= 2 -> offset
                offset > 2 -> offset + 1
                else -> offset
            }

            override fun transformedToOriginal(offset: Int): Int = when {
                offset <= 3 -> offset
                offset > 3 -> offset - 1
                else -> offset
            }
        }

        return TransformedText(AnnotatedString(out.toString()), offsetMapping)
    }
}