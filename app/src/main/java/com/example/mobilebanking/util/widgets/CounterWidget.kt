//package com.example.mobilebanking.util.widgets
//
//import android.content.Context
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.datastore.preferences.core.intPreferencesKey
//import androidx.glance.Button
//import androidx.glance.ButtonDefaults
//import androidx.glance.GlanceId
//import androidx.glance.GlanceModifier
//import androidx.glance.Image
//import androidx.glance.ImageProvider
//import androidx.glance.action.ActionParameters
//import androidx.glance.appwidget.GlanceAppWidget
//import androidx.glance.appwidget.action.ActionCallback
//import androidx.glance.appwidget.provideContent
//import androidx.glance.appwidget.state.updateAppWidgetState
//import androidx.glance.background
//import androidx.glance.currentState
//import androidx.glance.layout.Alignment
//import androidx.glance.layout.Column
//import androidx.glance.layout.Row
//import androidx.glance.layout.Spacer
//import androidx.glance.layout.fillMaxWidth
//import androidx.glance.layout.height
//import androidx.glance.layout.padding
//import androidx.glance.layout.width
//import androidx.glance.text.FontFamily
//import androidx.glance.text.Text
//import androidx.glance.text.TextStyle
//import androidx.glance.unit.ColorProvider
//import com.example.mobilebanking.R
//import com.example.mobilebanking.ui.theme.MobileBankingTheme
//import com.example.mobilebanking.ui.theme.black
//import com.example.mobilebanking.ui.theme.textColor
//import com.example.mobilebanking.ui.theme.white
//
//object CounterWidget : GlanceAppWidget() {
//    const val KEY_WIDGET_COUNT = "count"
//    val countKey = intPreferencesKey(KEY_WIDGET_COUNT)
//
//    override suspend fun provideGlance(context: Context, id: GlanceId) {
//        // Widget content generation logic
//        // ...
//        provideContent() {
//            MobileBankingTheme{
//                val count = currentState(key = countKey) ?: 1
//                val goal = 10
//                val countPercent = count * 100 / goal
//                context.WidgetContent(count = count, countPercent = countPercent)
//            }
//        }
//    }
//
//    @Composable
//    fun Context.WidgetContent(count: Int, countPercent: Int) {
//        // Composable content for widget with increment button
//
//        Column(
//            modifier = GlanceModifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .padding(4.dp)
//                .background(white),
//            verticalAlignment = Alignment.Vertical.CenterVertically,
//            horizontalAlignment = Alignment.Horizontal.CenterHorizontally
//        ) {
//
//
//            Row(modifier = GlanceModifier.fillMaxWidth()) {
//                Text(
//                    modifier = GlanceModifier.padding(start = 12.dp, top = 12.dp),
//                    text = stringResource(id = R.string.paynet_carta),
//                    style = TextStyle(
//                        color = ColorProvider(black),
//                        fontFamily = FontFamily("serif")
//                    )
//                )
//            }
//
//            Row(
//                modifier = GlanceModifier
//                    .fillMaxWidth()
//                    .padding(start = 9.dp, top = 4.dp)
//            ) {
//                Image(
//                    modifier = GlanceModifier
//                        .width(72.dp)
//                        .height(56.dp),
//                    provider = ImageProvider(R.drawable.ic_paynet),
//                    contentDescription = ""
//                )
//                Column(
//                    modifier = GlanceModifier
//                        .fillMaxWidth()
//                        .padding(start = 4.dp)
//                ) {
//
//                    Text(
//                        text = stringResource(id = R.string.paynet_carta),
//                        style = TextStyle(
//                            color = ColorProvider(textColor),
//                            fontFamily = FontFamily("serif"),
//                            fontSize = 24.sp
//                        )
//                    )
//                    Row(
//                        modifier = GlanceModifier
//                            .fillMaxWidth()
//                    ) {
//                        Text(
//                            text = "0",
//                            style = TextStyle(
//                                color = ColorProvider(textColor),
//                                fontFamily = FontFamily("serif"),
//                                fontSize = 20.sp
//                            )
//                        )
//                        Spacer(modifier = GlanceModifier.width(8.dp))
//                        Text(
//                            text = stringResource(id = R.string.som),
//                            style = TextStyle(
//                                color = ColorProvider(textColor),
//                                fontFamily = FontFamily("serif"),
//                                fontSize = 20.sp
//                            )
//                        )
//                    }
//                }
//            }
//
//
//
//            Button(
//                text = getString(R.string.card_number),
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = ColorProvider(Color.Blue),
//                    ColorProvider(white)
//                ),
//                onClick = {}
//            )
//
//
//
//
//        }
//    }
//
//    class IncrementActionCallback : ActionCallback {
//        override suspend fun onAction(
//            context: Context,
//            glanceId: GlanceId,
//            parameters: ActionParameters
//        ) {
//
//
//            updateAppWidgetState(context, glanceId) { prefs ->
//                val currentCount = prefs[countKey]
//
//                currentCount?.let {
//                    prefs[countKey] = currentCount + 1
//                } ?: run {
//                    prefs[countKey] = 1
//                }
//
//            }
//            CounterWidget.update(context, glanceId)
//        }
//    }
//
//}
