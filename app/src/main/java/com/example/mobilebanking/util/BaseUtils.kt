package com.example.mobilebanking.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Window
import androidx.biometric.BiometricPrompt
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentActivity
import com.example.mobilebanking.R
import com.example.mobilebanking.data.local.entity.LastTransferUserEntity
import com.example.mobilebanking.data.model.MarkerData
import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.util.biometric.BiometricAuthenticator
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.experimental.ExperimentalTypeInference

fun myLog(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

val PHONE_NUMBER_LENGTH = 9

fun Activity.changeColorStatusBar(color: Int = R.color.black, bool: Boolean) {
    val window: Window = this.window
    val decorView = window.decorView
    val wic = WindowInsetsControllerCompat(window, decorView)
    wic.isAppearanceLightStatusBars = bool
    window.statusBarColor = ContextCompat.getColor(this, color)
}


fun <T> List<T>.new(): MutableList<T> {
    return mutableListOf<T>().also { it.addAll(this) }
}

fun String.hidePartOfNumber(): String {

    if (this.length == 9) {
        return "+998 ${this.substring(0, 2)} ••• •• ${this.substring(7, 9)}"
    }

    return this
}

fun Context.vibrate(duration: Long) {
    val vibrator = ContextCompat.getSystemService(this, Vibrator::class.java)

    if (
        vibrator != null
        && vibrator.hasVibrator()
        && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    ) {

        val vibrationEffect =
            VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)

    }
}

fun Context.requireBiometricAuth(
    onSuccess: (result: BiometricPrompt.AuthenticationResult) -> Unit,
    onFailed: () -> Unit,
    onError: (errorCode: Int, errString: CharSequence) -> Unit
) {
    val biometricAuthenticator = BiometricAuthenticator(this)

    biometricAuthenticator.promptBiometricAuth(
        title = "Barmoq izi bo'yicha avtorizatsiya",
        subTitle = "Verify identity\nTouch ID",
        negativeButtonText = "BEKOR QILISH",
        fragmentActivity = this as FragmentActivity,
        onSuccess = onSuccess,
        onError = onError,
        onFailed = onFailed,
    )
}

fun openWebsite(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

fun makePhoneCall(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}

fun Context.openMap(data: MarkerData) {
    val uri = Uri.parse("geo:${data.lat},${data.lng}?z=${12}")
    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
    mapIntent.resolveActivity(packageManager)?.let {
        startActivity(Intent.createChooser(mapIntent, "Открыть с помощью"))
    }
}

fun openEmailIntent(context: Context, emailAddress: String, subject: String, body: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$emailAddress")
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    context.startActivity(intent)
}

fun String.checkExpirationDateValidation(): Boolean {
    try {
        if (this.substring(0, 2).toInt() > 12) return false

        val currentDate = Date()
        val dateFormat = SimpleDateFormat("MMyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)

        val monthsNow =
            formattedDate.substring(0, 2).toInt() + formattedDate.substring(2, 4).toInt() * 12
        val monthsUser =
            this.substring(0, 2).toInt() + this.substring(2, 4).toInt() * 12 // this is like a MM/YY
        return monthsUser >= monthsNow
    } catch (e: Exception) {
        return false
    }
}

fun String.addSpacesEveryAmount(): String {
    val stringBuilder = StringBuilder()
    val length = this.length

    for (i in this.indices) {
        if (i > 0 && (length - i) % 3 == 0) {
            stringBuilder.append(" ")
        }
        stringBuilder.append(this[i])
    }
    return stringBuilder.toString()
}

fun getGradient(type: Int): Brush = when (type) {
    0 -> {
        Brush.verticalGradient(listOf(Color(0xFF0063B5), Color(0xFF00EBC8)))
    }

    1 -> {
        Brush.verticalGradient(listOf(Color(0xFF06693a), Color(0xFF20d970)))
    }

    2 -> {
        Brush.verticalGradient(listOf(Color(0xFF5b0a8a), Color(0xFFa9518d)))
    }

    3 -> {
        Brush.verticalGradient(listOf(Color(0xFF930709), Color(0xFFff9c63)))
    }

    4 -> {
        Brush.verticalGradient(listOf(Color(0xFF886e33), Color(0xFFffd645)))
    }

    5 -> {
        Brush.verticalGradient(listOf(Color(0xFF282a75), Color(0xFF009ffd)))
    }

    6 -> {
        Brush.verticalGradient(listOf(Color(0xFF191a1f), Color(0xFF55555f)))
    }

    7 -> {
        Brush.verticalGradient(listOf(Color(0xFF6c0f17), Color(0xFFbd1373)))
    }

    else -> {
        Brush.verticalGradient(listOf(Color(0xFFa95403), Color(0xFFecbe38)))
    }
}

fun getCardType(cardNumbers: String): Int {
    return if (cardNumbers.startsWith("9860")) R.drawable.ic_paymentsystem_humo
    else if (cardNumbers.startsWith("8600")) R.drawable.ic_paymentsystem_uzcard
    else if (cardNumbers.startsWith("5440")) R.drawable.ic_paymentsystem_uzcard
    else if (cardNumbers.startsWith("5614")) R.drawable.ic_paymentsystem_uzcard
    else R.drawable.ic_paymentsystem_humo
}

val gson = Gson()

fun LastTransferUserEntity.toUIData() =
    UserCardData(0, pan, owner)

fun <T> Response<T>.toResult(): Result<T> {
    return if (isSuccessful) {
        body()?.let {
            Result.success(it)
        } ?: Result.failure(Exception("Response body is null!"))
    } else {
        Result.failure(Exception("Error occurred: ${errorBody()?.string() ?: "Unknown error"}"))
    }
}

context(FlowCollector<Result<T>>)
suspend fun <T> Result<T>.emitWith() {
    emit(this)
}

@OptIn(ExperimentalTypeInference::class)
fun <T> safetyFlow(@BuilderInference block: suspend FlowCollector<Result<T>>.() -> Unit): Flow<Result<T>> =
    flow {
        block()
    }
        .flowOn(Dispatchers.IO)
        .catch { exception ->
            emit(Result.failure(exception))
        }

fun String.formatPhoneNumber(): String {
    if (this.length == 13 && this.startsWith("+")) {
        return this.substring(0, 4) + " " +
                this.substring(4, 6) + " " +
                this.substring(6, 9) + " " +
                this.substring(9, 11) + " " +
                this.substring(11)
    }
    return ""
}
fun hideCardNumbers(cardNumbers: String) : String {
    var newStr = ""
    if (cardNumbers.length == 16) {
        newStr += cardNumbers.substring(0, 4)
        newStr += " "
        newStr += cardNumbers.substring(4, 6)
        newStr += "** "
        newStr += "**** "
        newStr += cardNumbers.substring(12)
    }
    return newStr
}
fun moneyAmountIsCorrect(amount: String) : Boolean =
    amount.toInt() in 1000 .. 12400000

fun getCurrentLanguage(): String {
    return Locale.getDefault().language
}

fun setLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val configuration = Configuration(context.resources.configuration)
    configuration.setLocale(locale)
    context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
}
fun builderAnnotatedString(value: String, startWord: String, distance: Int) : AnnotatedString {
    return buildAnnotatedString {
        val startIndex = value.indexOf(startWord)
        val endIndex = startIndex + distance

        append(value)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                textDecoration = TextDecoration.Underline
            ), start = startIndex, end = endIndex
        )
    }
}