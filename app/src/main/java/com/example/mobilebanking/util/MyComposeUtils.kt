package com.example.mobilebanking.util

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.example.mobilebanking.ui.componnent.text.TextBold
import com.example.mobilebanking.ui.theme.black
import com.example.mobilebanking.ui.theme.grayIcon
import com.example.mobilebanking.ui.theme.primaryColor


@Composable
fun RowScope.TabNavigatorItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val interactionSource = remember { MutableInteractionSource() }


    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                tint = if (tabNavigator.current == tab) primaryColor else black,
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
        label = {
            TextBold(
                text = tab.options.title,
                fontSize = 10.sp,
                color = grayIcon
            )
        },
    )

}



