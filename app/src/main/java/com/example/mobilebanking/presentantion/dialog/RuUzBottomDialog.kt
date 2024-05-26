package com.example.mobilebanking.presentantion.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.gray
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.getCurrentLanguage

class RuUzBottomDialog(
    val clickUz: () -> Unit,
    val clickRu: () -> Unit,
    val clickCancel: () -> Unit
) : Screen {

    @Composable
    override fun Content(

    ) {
        RuUzBottomDialogContent(
            clickCancel = { clickCancel.invoke() },
            clickRu = {clickRu.invoke()},
            clickUz = {clickUz.invoke()}
        )
    }
}

@Composable
fun RuUzBottomDialogContent(
    clickUz: () -> Unit,
    clickRu: () -> Unit,
    clickCancel: () -> Unit
) {
    var ruInUzState by remember { mutableStateOf(getCurrentLanguage() == "uz") }
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(white)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(6.dp)
                    .width(44.dp)
                    .clip(CircleShape)
                    .background(gray)
                    .align(Alignment.TopCenter)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_cencel), contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .clip(CircleShape)
                    .background(gray)
                    .padding(4.dp)
                    .clickable {
                        clickCancel.invoke()
                    },
            )
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.appLanguage),
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        val modifierStateFalse = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        val modifierStateTrue = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
        Row(
            modifier = if (ruInUzState) {
                modifierStateFalse
                    .background(white)
                    .clickable {
                        ruInUzState = false
                        clickRu.invoke()

                    }
            } else {
                modifierStateTrue
                    .background(white)
                    .clickable {}
            }


        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_russian), contentDescription = "ru",
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.russia),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (!ruInUzState) {
                Image(
                    painter = painterResource(id = R.drawable.ic_check), contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 16.dp)
                )
            }

        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = if (!ruInUzState) {
                modifierStateFalse
                    .background(white)
                    .clickable {
                        ruInUzState = true
                        clickUz.invoke()
                    }
            } else {
                modifierStateTrue
                    .background(white)

            }


        ) {
            Image(
                painter = painterResource(id = R.drawable.uz), contentDescription = "uz",
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .size(36.dp)
            )
            Text(
                text = stringResource(id = R.string.uzbek),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (ruInUzState) {
                Image(
                    painter = painterResource(id = R.drawable.ic_check), contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(36.dp))


    }
}

@Preview
@Composable
fun BottomDialogPreview() {
    MobileBankingTheme {
        RuUzBottomDialogContent({},{},{})
    }
}