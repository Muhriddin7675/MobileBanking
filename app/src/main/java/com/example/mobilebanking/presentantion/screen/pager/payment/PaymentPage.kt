package com.example.mobilebanking.presentantion.screen.pager.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.TemplateData
import com.example.mobilebanking.ui.componnent.AddItem
import com.example.mobilebanking.ui.componnent.CardAvia
import com.example.mobilebanking.ui.componnent.CardStartTextEndImage
import com.example.mobilebanking.ui.componnent.CardTextEndIcon
import com.example.mobilebanking.ui.componnent.CardTopTextBottomImage
import com.example.mobilebanking.ui.componnent.TrainTicked
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.callCardColor
import com.example.mobilebanking.ui.theme.communalPaymentCardColor
import com.example.mobilebanking.ui.theme.educationCardColor
import com.example.mobilebanking.ui.theme.factPaymentCardColor
import com.example.mobilebanking.ui.theme.internetTVCardColor
import com.example.mobilebanking.ui.theme.publicServicesCardColor
import com.example.mobilebanking.ui.theme.qrCodCardColor
import com.example.mobilebanking.ui.theme.requisiteCardColor
import com.example.mobilebanking.ui.theme.textInputColor
import com.example.mobilebanking.ui.theme.trainTicketCardColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.myLog
import org.orbitmvi.orbit.compose.collectAsState


object PaymentPage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Payment"
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_operations_wallet))

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val screenModel: PaymentContract.Model = getViewModel<PaymentModel>()

        myLog("PaymentPage -> $this")
        myLog("PaymentPage screenModel -> $screenModel")

        MobileBankingTheme {
            val uiState = screenModel.collectAsState().value
            PaymentContent(uiState = uiState, onEventDispatcher = screenModel::onEventDispatcher)
        }
    }
}

@Composable
private fun PaymentContent(
    uiState: PaymentContract.UIState,
    onEventDispatcher: (PaymentContract.Intent) -> Unit
) {
    val items = List(1) { index -> "Item $index" }
    val ls = listOf(
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),
        TemplateData("Qo'shish", R.drawable.ic_add_black_cricl),

        )
//        var text by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .background(white)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.payment),
                fontSize = 32.sp,
                color = black,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                modifier = Modifier.weight(1f)
            )

            Text(
                text = stringResource(id = R.string.cancellation),
                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                color = black,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(white)
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                    .clickable { }
            )

        }
        Spacer(modifier = Modifier.height(18.dp))

        var telefonAndCardNumber by remember { mutableStateOf("") }
        Box(modifier = Modifier.padding(horizontal = 16.dp)) {

            BasicTextField(
                value = telefonAndCardNumber,
                onValueChange = {
                    telefonAndCardNumber = it
                },
                textStyle = TextStyle(
                    color = black,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = textInputColor, shape = RoundedCornerShape(16.dp))
                    .padding(14.dp)
                    .padding(start = 42.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                    }
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_navigation_search_x24),
                contentDescription = "search",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.templates),
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            fontSize = 20.sp,
            color = black,
            modifier = Modifier.padding(start = 16.dp)
        )

        LazyRow {
            items(ls.size) {
                AddItem(
                    modifier = Modifier
                        .height(116.dp)
                        .width(90.dp)
                        .clickable { },
                    text = ls[it].text,
                    icon = ls[it].icon
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
        ) {


            CardStartTextEndImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 6.dp)
                    .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(qrCodCardColor)
                    .clickable {},
                text = R.string.qr_code, image = R.drawable.internet
            )

            CardStartTextEndImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 6.dp)
                    .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(factPaymentCardColor)
                    .clickable {},
                text = R.string.quick_payment,
                image = R.drawable.self_transfer_to_wallet
            )
        }
        TrainTicked(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(trainTicketCardColor)
                .clickable { },
            onClickButton = { },
            titleText = stringResource(id = R.string.train_ticket),
            text = stringResource(id = R.string.comfort_quick),
            buttonText = stringResource(id = R.string.buy),
            icon = R.drawable.paynet_railway,
            color = trainTicketCardColor
        )
        CardAvia(clickButton = {}, clickAviaCard = {})

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.payment_types),
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            fontSize = 20.sp,
            color = black,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 6.dp)
            ) {

                CardTopTextBottomImage(
                    icon = R.drawable.communication,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(callCardColor)
                        .height(170.dp)
                        .clickable {},
                    textTitle = stringResource(
                        id = R.string.call
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))


                CardTopTextBottomImage(
                    icon = R.drawable.internet,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(internetTVCardColor)
                        .height(121.dp)
                        .clickable {},
                    textTitle = stringResource(
                        id = R.string.InternetTV
                    ),
                )
                Spacer(modifier = Modifier.height(12.dp))

                CardTopTextBottomImage(
                    icon = R.drawable.education,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(educationCardColor)
                        .height(121.dp)
                        .clickable {},
                    textTitle = stringResource(
                        id = R.string.education
                    ),
                )

            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 6.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.request),
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(requisiteCardColor)
                        .padding(12.dp),
                    fontSize = 18.sp,
                    color = black,
                    fontFamily = FontFamily(Font(R.font.pnfont_regular))
                )

                Spacer(modifier = Modifier.height(12.dp))

                CardTopTextBottomImage(
                    icon = R.drawable.communal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(communalPaymentCardColor)
                        .height(170.dp)
                        .clickable {},
                    textTitle = stringResource(
                        id = R.string.communal
                    ),
                    boolImageStartInEnd = false
                )
                Spacer(modifier = Modifier.height(12.dp))

                CardTopTextBottomImage(
                    icon = R.drawable.government,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 1.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(publicServicesCardColor)
                        .height(170.dp)
                        .clickable {},
                    textTitle = stringResource(
                        id = R.string.public_Services
                    ),
                    boolImageStartInEnd = false
                )
            }

        }
        Spacer(modifier = Modifier.height(12.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                CardTextEndIcon(
                    icon = R.drawable.ic_operations_timecircle,
                    text = stringResource(id = R.string.term_payment),
                    clickItem = {

                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                CardTextEndIcon(
                    icon = R.drawable.ic_operations_timecircle,
                    text = stringResource(id = R.string.credit_payment),
                    clickItem = {

                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                CardTextEndIcon(
                    icon = R.drawable.ic_operations_timecircle,
                    text = stringResource(id = R.string.mik_credit),
                    clickItem = {

                    }
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                CardTextEndIcon(
                    icon = R.drawable.ic_operations_timecircle,
                    text = stringResource(id = R.string.transport_taxi),
                    clickItem = {

                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                CardTextEndIcon(
                    icon = R.drawable.ic_operations_timecircle,
                    text = stringResource(id = R.string.medicine),
                    clickItem = {

                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                CardTextEndIcon(
                    icon = R.drawable.ic_operations_timecircle,
                    text = stringResource(id = R.string.another_payment),
                    clickItem = {

                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(56.dp))

    }
}

@Composable
@Preview
private fun PaymentContentPreview() {
    MobileBankingTheme {
        PaymentContent(uiState = PaymentContract.UIState.InitState) {}
    }
}



