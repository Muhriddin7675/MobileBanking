package com.example.mobilebanking.presentantion.screen.pager.transfer

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
import androidx.compose.ui.graphics.Color
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
import com.example.mobilebanking.data.TemplateData
import com.example.mobilebanking.presentantion.screen.pager.transfer.TransfersPage.TransfersContent
import com.example.mobilebanking.ui.componnent.AddItem
import com.example.mobilebanking.ui.componnent.CardStartTextEndImage
import com.example.mobilebanking.ui.theme.MobileBankingTheme
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.myCardColor
import com.example.mobilebanking.ui.theme.paymentCard
import com.example.mobilebanking.ui.theme.rosCardColor
import com.example.mobilebanking.ui.theme.textInputColor
import com.example.mobilebanking.ui.theme.uzCardColor
import com.example.mobilebanking.ui.theme.white
import com.example.mobilebanking.util.myLog
import org.orbitmvi.orbit.compose.collectAsState

object TransfersPage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "O'tkazmalar"
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_action_transfers))

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
        val screenModel: TransferContract.Model = getViewModel<TransferModel>()

        myLog("DeliveredPage -> $this")
        myLog("DeliveredPage screenModel -> $screenModel")

        MobileBankingTheme {
            val uiState = screenModel.collectAsState().value
            TransfersContent(uiState = uiState, onEventDispatcher = screenModel::onEventDispatcher)
        }
    }


    @Composable
    fun TransfersContent(
        uiState: TransferContract.UIState,
        onEventDispatcher: (TransferContract.Intent) -> Unit

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
                    text = stringResource(id = R.string.transfers),
                    fontSize = 32.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = stringResource(id = R.string.cancellation),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
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
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = textInputColor, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp)
                        .padding(end = 64.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                        }
                    )
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_contacts),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_scan),
                        contentDescription = ""
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.templates),
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp)
            )

            LazyRow {
                items(ls.size) {
                    AddItem(
                        modifier = Modifier
                            .height(116.dp)
                            .width(90.dp),
                        text = ls[it].text,
                        icon = ls[it].icon
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {


                CardStartTextEndImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 6.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(myCardColor)
                        .clickable {},
                    text = R.string.my_cards, image = R.drawable.self_transfer_to_card
                )

                CardStartTextEndImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 6.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(paymentCard),
                    text = R.string.payment_card,
                    image = R.drawable.self_transfer_to_wallet
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
                            .width(90.dp),
                        text = ls[it].text,
                        icon = ls[it].icon
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(R.string.international_transfers),
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                fontSize = 20.sp,
                color = black
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 6.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(rosCardColor)
                ) {
                    Text(
                        text = stringResource(R.string.russia),
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 12.dp
                        ),
                        color = black,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        fontSize = 20.sp
                    )
                    Image(
                        painter = painterResource(R.drawable.uzb_to_ru),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(6.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 6.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(uzCardColor)
                ) {
                    Text(
                        text = stringResource(R.string.uzbekistan),
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 12.dp
                        ),
                        color = black,
                        fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                        fontSize = 20.sp
                    )
                    Image(
                        painter = painterResource(R.drawable.ru_to_uzb),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(6.dp)

                    )
                }
            }
            Spacer(modifier = Modifier.height(72.dp))


        }
    }
}

@Composable
@Preview
private fun PaymentContentPreview() {
    MobileBankingTheme {
        TransfersContent(uiState = TransferContract.UIState.InitState) {}
    }
}
