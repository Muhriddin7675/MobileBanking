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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobilebank.ui.components.CustomImageView
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.text.CustomTextView

@Composable
fun AddCardItem (
    modifier: Modifier = Modifier
) {
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
                    .background(Color(0xFF71CC75), shape = RoundedCornerShape(8.dp))
            ) {
                Box(
                    Modifier
                        .align(Alignment.Center)
                        .background(color = Color(0xFF479C4A), shape = RoundedCornerShape(100))
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(14.dp),
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 112.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth(),
            ) {
                CustomTextView(
                    text = stringResource(id = R.string.select_card),
                    fontSize = 18,
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
