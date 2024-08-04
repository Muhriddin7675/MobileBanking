package com.example.mobilebanking.presentantion.screen.transfer_success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebank.presentation.screens.transfer_success.TransferSuccessViewModel
import com.example.mobilebank.ui.components.CustomImageView
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.ui.componnent.card.CustomButton
import com.example.mobilebanking.ui.componnent.text.CustomTextView
import com.example.mobilebanking.ui.theme.selectedItemColor
import com.example.mobilebanking.util.addSpacesEveryAmount
import com.example.mobilebanking.util.getCardType
import com.example.mobilebanking.util.getGradient
import org.orbitmvi.orbit.compose.collectAsState

class TransferSuccessScreen(
    private val data: UserCardData,
    private val amount: Long
) : Screen {
    @Composable
    override fun Content() {
        val viewModel: TransferSuccessContract.ViewModel = getViewModel<TransferSuccessViewModel>()
        TransferSuccessContent(
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher,
            data = data,
            amount = amount
        )
    }
}

@Composable
private fun TransferSuccessContent(
    uiState: State<TransferSuccessContract.UIState>,
    onEventDispatcher: (TransferSuccessContract.Intent) -> Unit,
    data: UserCardData,
    amount: Long
) {
    val cardType = getCardType(data.pan)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextView(
                modifier = Modifier
                    .padding(top = 24.dp),
                text = stringResource(id = R.string.transfer_success),
                fontSize = 24,
                fontWeight = 600
            )
            CustomImageView(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(70.dp),
                src = R.drawable.ic_success
            )

            CustomTextView(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = amount.toString().addSpacesEveryAmount(),
                fontSize = 42,
                fontWeight = 600
            )

            CustomTextView(
                modifier = Modifier
                    .padding(top = 0.dp),
                text = stringResource(id = R.string.som),
                fontSize = 28,
                fontWeight = 600,
                color = Color(0xFF8B8B8B)
            )

            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 36.dp)
                    .fillMaxWidth()
                    .height(72.dp),
                elevation = CardDefaults.elevatedCardElevation(3.dp),
                colors = CardDefaults.cardColors(Color.White),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .width(84.dp)
                            .height(56.dp)
                            .align(Alignment.CenterStart)
                            .background(brush = getGradient(0), shape = RoundedCornerShape(8.dp))
                    ) {
                        CustomImageView(
                            modifier = Modifier
                                .padding(
                                    end = 8.dp,
                                    bottom = if (cardType == R.drawable.ic_paymentsystem_uzcard) 4.dp else 8.dp
                                )
                                .width(40.dp)
                                .align(Alignment.BottomEnd),
                            src = cardType
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 112.dp)
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                    ) {
                        CustomTextView(
                            text =  data.owner,
                            fontSize = 16,
                            fontWeight = 600,
                            color = Color(0xFF6B6B6B)
                        )

                        CustomTextView(
                            text = "• ${data.pan.substring(12)}",
                            fontSize = 18,
                            fontWeight = 600
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(end = 12.dp, bottom = 10.dp)
                            .align(Alignment.BottomEnd)
                            .background(Color(0xFFC3FAC5), shape = RoundedCornerShape(16.dp))
                    ) {
                        CustomTextView(
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 2.dp)
                                .align(Alignment.Center),
                            text = stringResource(id = R.string.done),
                            color = Color(0xFF4CAF50),
                            fontSize = 14,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .background(Color(0xFFE4E3E3), shape = RoundedCornerShape(16.dp))
            ) {
                CustomTextView(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .align(Alignment.Center),
                    text = stringResource(id = R.string.transfer_percent) + " ${amount/100} " + stringResource(id = R.string.som),
                    color = Color(0xFF535353),
                    fontSize = 16,
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 36.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = 16.dp, bottom = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .size(26.dp),
                        painter = painterResource(id = R.drawable.ic_operations_star_v2),
                        contentDescription = null
                    )

                    CustomTextView(
                        modifier = Modifier
                            .width(68.dp),
                        maxLines = 2,
                        text = stringResource(id = R.string.add_shablon),
                        textAlign = TextAlign.Center,
                        fontSize = 14
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.ic_detail),
                        contentDescription = null
                    )

                    CustomTextView(
                        modifier = Modifier
                            .width(68.dp),
                        maxLines = 2,
                        text = stringResource(id = R.string.transferDetails),
                        textAlign = TextAlign.Center,
                        fontSize = 14
                    )
                }
            }

            CustomButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                ,
                text = stringResource(id = R.string.success),
                fontSize = 20,
                fontWeight = 600,
                textColor = Color.White,
                colors = ButtonDefaults
                    .buttonColors(selectedItemColor)
            ) { onEventDispatcher(TransferSuccessContract.Intent.BackMainScreen) }
        }
    }
}


@Preview
@Composable
fun Preview() {
    TransferSuccessContent(
        uiState = remember {
            mutableStateOf(TransferSuccessContract.UIState())
        },
        onEventDispatcher = {},
        data = UserCardData(0, "9999 9999 9999 9999", "Valiyev Muhriddin"),
        amount = 100000
    )
}