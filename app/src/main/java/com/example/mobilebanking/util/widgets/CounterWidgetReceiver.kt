package com.example.mobilebanking.util.widgets

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver


class CounterWidgetReceiver:GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = CounterWidget

}

/*
Column(
modifier = GlanceModifier
.fillMaxSize()
.padding(start = 16.dp, end = 16.dp)
.background(white)
.clickable {
    actionStartActivity<MainActivity>()
},
verticalAlignment = Alignment.Vertical.CenterVertically,
horizontalAlignment = Alignment.Horizontal.CenterHorizontally
) {
    Row(modifier = GlanceModifier.fillMaxWidth()) {
        Text(
            modifier = GlanceModifier.padding(start = 12.dp, top = 12.dp),
            text = stringResource(id = R.string.paynet_carta),
            style = TextStyle(
                color = ColorProvider(black),
                fontFamily = FontFamily("serif")
            )
        )
    }
    Row(
        modifier = GlanceModifier
            .fillMaxWidth()
            .padding(start = 9.dp, top = 4.dp)
    ) {
        Image(
            modifier = GlanceModifier
                .width(72.dp)
                .height(56.dp),
            provider = ImageProvider(R.drawable.ic_paynet),
            contentDescription = ""
        )
        Column(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(start = 4.dp)
        ) {

            Text(
                text = stringResource(id = R.string.paynet_carta),
                style = TextStyle(
                    color = ColorProvider(textColor),
                    fontFamily = FontFamily("serif"),
                    fontSize = 24.sp
                )
            )
            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "0",
                    style = TextStyle(
                        color = ColorProvider(textColor),
                        fontFamily = FontFamily("serif"),
                        fontSize = 20.sp
                    )
                )
                Spacer(modifier = GlanceModifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.som),
                    style = TextStyle(
                        color = ColorProvider(textColor),
                        fontFamily = FontFamily("serif"),
                        fontSize = 20.sp
                    )
                )
            }
        }
    }
}*/
