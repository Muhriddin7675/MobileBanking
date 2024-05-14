package com.example.mobilebanking.util.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.widget.RemoteViews
import com.example.mobilebanking.MainActivity
import com.example.mobilebanking.R
import com.example.mobilebanking.data.currency.CurrencyThread

/**
 * Implementation of App Widget functionality.
 */
class AppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.app_widget)
    CurrencyThread {ls ->
        ls.forEach{it ->
            if(it.Ccy == "USD"){
                views.setTextViewText(R.id.text_usd,it.Rate)
            }
            if(it.Ccy == "EUR"){
                views.setTextViewText(R.id.text_eur,it.Rate)
            }
            if(it.Ccy == "RUB"){
                views.setTextViewText(R.id.text_rub,it.Rate)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }
    }
    views.setTextViewText(R.id.text_date, getCurrentDate())

    val configIntent = Intent(context, MainActivity::class.java)
    val configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, PendingIntent.FLAG_IMMUTABLE)
    views.setOnClickPendingIntent(R.id.widgets_container,configPendingIntent)

    // Refresh button
    val refreshIntent = Intent(context, AppWidgetProvider::class.java)
    refreshIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
    refreshIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(appWidgetId))
    val refreshPendingIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, PendingIntent.FLAG_IMMUTABLE)
    views.setOnClickPendingIntent(R.id.btn_refresh, refreshPendingIntent)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val monthString = if (month < 10) "0$month" else "$month"
    val dayString = if (day < 10) "0$day" else "$day"
    return "$dayString.$monthString.$year y"
}