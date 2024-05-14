package com.example.mobilebanking.util
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Window
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentActivity
import com.example.mobilebanking.R
import com.example.mobilebanking.data.MarkerData
import com.example.mobilebanking.util.biometric.BiometricAuthenticator
import timber.log.Timber

fun myLog(message : String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

val PHONE_NUMBER_LENGTH = 9

fun Activity.changeColorStatusBar(color: Int = R.color.black, bool:Boolean) {
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

        val vibrationEffect = VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
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
