package com.example.mobilebanking.presentantion.screen.login

import RegisterScreenContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.ButtonComponent
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.disabledColor
import com.example.mobilebanking.ui.theme.primaryColor
import com.example.mobilebanking.ui.theme.textColorBlue
import com.example.mobilebanking.ui.theme.textInputColor
import org.orbitmvi.orbit.compose.collectAsState

class RegisterScreen : Screen {
    @Composable
    override fun Content() {

        val model: RegisterScreenContract.RegisterScreenModel = getViewModel<RegisterScreenModel>()

        val uiSate = model.collectAsState().value
        val context = LocalContext.current

        RegisterContent(model::onEventDispatcher)

    }
}

@Composable
fun RegisterContent(
    onEventDispatcher: (RegisterScreenContract.Intent) -> Unit = {}
) {
    var phoneNumber by remember { mutableStateOf("") }
    var buttonSt by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp, top = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(0.dp)
                    .background(textInputColor)
                    .padding(10.dp)

            ) {

                Text(
                    text = "Til", modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.uz),
                    contentDescription = "flag",
                    modifier = Modifier
                        .height(24.dp)
                        .width(36.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .align(Alignment.CenterVertically)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
//                Box(
//                    modifier = Modifier
//                        .size(72.dp)
//                        .align(Alignment.Center)
//                        .clip(RoundedCornerShape(36.dp)) // Corner radius
//                        .background(
//                            brush = Brush.horizontalGradient(
//                                colors = listOf(
//                                    circleStartColorGreen,
//                                    circleEndColorGreen
//                                ), // Start and end colors
//                                startX = 0f,
//                                endX = Float.POSITIVE_INFINITY
//                            )
//                        )
//                ) {
//                }
            Image(
                painter = painterResource(id = R.drawable.image_crickle_green),
                contentDescription = "image",
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Raqamingizni kiriting",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Mijoz yoki kirish uchun",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(36.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(0.dp)
                .background(textInputColor)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.uz),
                contentDescription = "flag",
                modifier = Modifier
                    .height(24.dp)
                    .width(32.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(4.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_chewron_down),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(24.dp)

            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "+ 998 ", fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                fontSize = 20.sp
            )

            BasicTextField(
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                    buttonSt = it.length == 7

                },
                textStyle = TextStyle(
                    color = LocalContentColor.current,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle "Done" button press */ }
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )// Text size

            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cencel),
                    contentDescription = "Cencel",
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }


        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                ButtonComponent(
                    text = "Davom etish",
                    onClicked = {
                        onEventDispatcher.invoke(
                            RegisterScreenContract.Intent.ClickNextButton(
                                phoneNumber
                            )
                        )
                    },
                    enabledColor = primaryColor,
                    disabledColor = disabledColor,
                    enabled = buttonSt,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Davome etish tumgasini bosih orqali men",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 14.sp
                )
                Text(
                    text = "Xizmatlar ko'rsatish haqidaig oferta shartlarini",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = textColorBlue,
                    fontSize = 14.sp
                )
                Text(
                    text = "qabul qilaman va shaxsiy malumotlarni qayta",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 14.sp
                )
                Text(
                    text = "ishlashga rozilik bildiraman",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 14.sp
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginContent() {
    MobileBankingTheme {
        RegisterContent()
    }

}


