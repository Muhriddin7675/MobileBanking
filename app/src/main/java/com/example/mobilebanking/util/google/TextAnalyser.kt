package com.example.mobilebanking.util.google

import android.graphics.Rect
import android.util.Log
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

@ExperimentalGetImage
class TextAnalyzer(
    private val drawRect: (List<Pair<String, Rect>>) -> Unit,
    private val initScale: (Double) -> Unit
) : ImageAnalysis.Analyzer {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    private var originalWidth: Int = 0
    private var originalHeight: Int = 0
    private var init = false

    override fun analyze(imageProxy: ImageProxy) {
        if (!init) {
            val scale = originalWidth.toDouble() / imageProxy.height
            init = true
            initScale(scale)
        }
        val mediaImage = imageProxy.image
        mediaImage?.let { mediaImage ->
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    val textWithRects = processTextBlocks(visionText)
                    drawRect(textWithRects)
                    imageProxy.close()
                }
                .addOnFailureListener { e ->
                    Log.e("TextAnalyzer", "Text recognition failed", e)
                    imageProxy.close()
                }
        } ?: run {
            imageProxy.close()
        }
    }

    private fun processTextBlocks(visionText: Text): List<Pair<String, Rect>> {
        val textWithRects = mutableListOf<Pair<String, Rect>>()
        val cardNumberPattern = "\\b\\d{4}[-\\s]?\\d{4}[-\\s]?\\d{4}[-\\s]?\\d{4}\\b".toRegex()
        val validityPeriodPattern = "\\b(0[1-9]|1[0-2])/(\\d{2})\\b".toRegex()
        val namePattern = "\\b([A-Za-z]+)\\s+([A-Za-z]+)\\b".toRegex()

        for (block in visionText.textBlocks) {
            val boundingBox = block.boundingBox
            val text = block.text

            // Extract card number
            val cardNumber = extractFirstMatch(text, cardNumberPattern)
            cardNumber?.let {
                textWithRects.add(Pair("Card Number: $it", boundingBox ?: Rect(0, 0, 0, 0)))
            }

            // Extract validity period
            val validityPeriod = extractFirstMatch(text, validityPeriodPattern)
            validityPeriod?.let {
                textWithRects.add(Pair("Validity Period: $it", boundingBox ?: Rect(0, 0, 0, 0)))
            }

            // Extract name
            val name = extractFirstMatch(text, namePattern)
            name?.let {
                textWithRects.add(Pair("Name: $it", boundingBox ?: Rect(0, 0, 0, 0)))
            }
        }
        return textWithRects
    }

    private fun extractFirstMatch(text: String, pattern: Regex): String? {
        val matchResult = pattern.find(text)
        return matchResult?.value
    }

    fun setOriginalDimensions(width: Int, height: Int) {
        originalWidth = width
        originalHeight = height
    }

    fun setScale(scale: Double) {
        initScale(scale)
    }
}
