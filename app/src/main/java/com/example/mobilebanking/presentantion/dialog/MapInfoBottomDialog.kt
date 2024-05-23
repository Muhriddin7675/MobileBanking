package com.example.mobilebanking.presentantion.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.MarkerData
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.gray
import com.example.mobilebanking.ui.theme.textColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.makePhoneCall
import com.example.mobilebanking.util.openMap
import com.example.mobilebanking.util.openWebsite

class MapInfoBottomDialog(
    val markerData: MarkerData,
    val clickCancel: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        MapInfoBottomContent(
            image = markerData.image,
            titleBank = markerData.titleBank,
            titleBankInfo = markerData.titleBankInfo,
            textLocation = markerData.textLocation,
            textTelephoneNumber = markerData.textTelephoneNumber,
            clickRotation = {

                clickCancel.invoke()
                context.openMap(data = markerData)
            },
            clickCall = {
                clickCancel.invoke()
                val phone = markerData.textTelephoneNumber.replace(" ", "")
                makePhoneCall(context, phone)
            },
            clickCancel = { clickCancel.invoke() },
            clickSite = {
                clickCancel.invoke()
                openWebsite(context = context, url = "https://www.paynet.uz")
            },
        )

    }

}


@Composable
fun MapInfoBottomContent(
    image: Int,
    titleBank: String,
    titleBankInfo: String,
    textLocation: String,
    textTelephoneNumber: String,
    clickRotation: () -> Unit,
    clickCall: () -> Unit,
    clickSite: () -> Unit,
    clickCancel: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
            .background(white)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Image(
                painter = painterResource(id = image), contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.ic_cencel), contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 16.dp, top = 16.dp)
                    .clip(CircleShape)
                    .background(gray)
                    .padding(4.dp)
                    .clickable {
                        clickCancel.invoke()
                    },
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = titleBank,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = titleBankInfo,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            fontSize = 16.sp,
            color = textColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(textColor)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            CartMapTextComponent(icon = R.drawable.ic_route, text = "Yo'nalishni rejalashtirish") {
                clickRotation.invoke()
            }
            Spacer(modifier = Modifier.height(16.dp))

            CartMapTextComponent(icon = R.drawable.ic_location, text = textLocation) {}
            Spacer(modifier = Modifier.height(16.dp))

            CartMapTextComponent(icon = R.drawable.ic_internet, text = "paynet.uz") {
                clickSite.invoke()
            }
            Spacer(modifier = Modifier.height(16.dp))

            CartMapTextComponent(
                icon = R.drawable.ic_action_phone_alt,
                text = textTelephoneNumber
            ) {
                clickCall.invoke()
            }
            Spacer(modifier = Modifier.height(24.dp))

        }

    }
}

@Preview
@Composable
fun MapInfoBottomPreview() {
    MobileBankingTheme {
        MapInfoBottomContent(
            image = R.drawable.image_llc_uzpaynet,
            titleBank = "\"UZPAYNET\" LLC | Платежная система",
            titleBankInfo = "Paynet",
            textLocation = "Muqimiy ko'chasi 59, Тоshkent, Toshkent, Узбекистан",
            textTelephoneNumber = "+998 71 202 07 07",
            {}, {}, {}, {}
        )
    }
}

@Composable
fun CartMapTextComponent(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onClick.invoke() }
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "support",
            modifier = Modifier
                .padding(start = 16.dp, end = 12.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 48.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_regular))
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.ic_right),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterVertically)
        )

    }
}




