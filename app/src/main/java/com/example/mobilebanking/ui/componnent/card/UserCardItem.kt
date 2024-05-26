package com.example.mobilebanking.ui.componnent.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobilebank.ui.components.CustomImageView
import com.example.mobilebanking.R
import com.example.mobilebanking.data.model.UserCardData
import com.example.mobilebanking.ui.componnent.text.CustomTextView
import com.example.mobilebanking.util.getCardType
import com.example.mobilebanking.util.getGradient
import com.example.mobilebanking.util.hideCardNumbers

@Composable
fun UserCardItem(
    modifier: Modifier = Modifier,
    data: UserCardData
) {
    val cardType = getCardType(data.pan)
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
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
                    text = data.owner,
                    fontSize = 18,
                    fontWeight = 600,
                    color = Color.Black
                )

                CustomTextView(
                    text = if (data.pan.length == 16) hideCardNumbers(data.pan) else "**** **** **** ${data.pan}",
                    fontSize = 14,
                    color = Color(0xFF6B6B6B)
                )
            }

            CustomImageView(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
                    .align(Alignment.CenterEnd),
                src = R.drawable.ic_right
            )
        }
    }
}
