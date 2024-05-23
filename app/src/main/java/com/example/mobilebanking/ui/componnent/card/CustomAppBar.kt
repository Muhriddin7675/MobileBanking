package com.example.mobilebanking.ui.componnent.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.componnent.CustomTextView
import com.example.mobilebanking.ui.theme.appBackgroundColorWhite
import com.example.mobilebanking.ui.theme.black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: String,
    fontSize: Int = 20,
    containerColor: Color = appBackgroundColorWhite,
    titleContentColor: Color = black,
    actionIconContentColor: Color = black,
    titleMargin: PaddingValues = PaddingValues(start = 16.dp),
    iconMargin: PaddingValues = PaddingValues(start = 16.dp),
    onClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults
                    .topAppBarColors(containerColor = containerColor),
                title = {
                    CustomTextView(
                        modifier = Modifier
                            .padding(titleMargin),
                        text = title,
                        fontSize = fontSize,
                        color = titleContentColor
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(iconMargin)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onClick() },
                        painter = painterResource(id = R.drawable.ic_navigation_arrow_left_x24),
                        contentDescription = null,
                        tint = actionIconContentColor
                    )
                }
            )
        },
        modifier = Modifier.background(containerColor),
        content = content
    )
}
