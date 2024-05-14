package com.example.mobilebanking.presentantion.dialog

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

class AddCardBottomDialog(
    val clickRuCard: () -> Unit,
    val clickUzCard: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        AddCardBottomDialogContent(
            clickRuCard = { clickRuCard.invoke() },
            clickUzCard = { clickUzCard.invoke() }
        )

    }

}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun AddCardBottomDialogContent(
    clickRuCard: () -> Unit,
    clickUzCard: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(white)
    ) {
        Box(
            modifier = Modifier
                .height(8.dp)
                .width(44.dp)
                .padding(top = 4.dp)
                .clip(CircleShape)
                .background(gray)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
            Text(
                text = stringResource(id = R.string.howToAddCard),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp),
                fontFamily = FontFamily(Font(R.font.pnfont_semibold))
            )
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(R.drawable.ic_cencel), contentDescription = "",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.CenterVertically),
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = true,
                    onClickLabel = "",
                    role = null,
                    onClick = { clickUzCard.invoke() })
        ) {
            Image(
                painter = painterResource(R.drawable.image_uz_cricl), contentDescription = "",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(32.dp),
            )
            Text(
                text = stringResource(id = R.string.uzCard),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
            )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    enabled = true,
                    onClickLabel = "",
                    role = null,
                    onClick = { clickRuCard.invoke() })
        ) {
            Image(
                painter = painterResource(R.drawable.ic_russion_rubl), contentDescription = "",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(32.dp),
            )
            Text(
                text = stringResource(id = R.string.ruCard),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
                fontFamily = FontFamily(Font(R.font.pnfont_regular))
            )
        }
        Spacer(modifier = Modifier.height(24.dp))


    }
}
@Preview
@Composable
fun AddCardBottomDialogPreview() {
    MobileBankingTheme {
        AddCardBottomDialogContent({}, {})
    }
}
