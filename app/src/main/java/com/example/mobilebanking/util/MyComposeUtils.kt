package com.example.mobilebanking.util

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.example.mobilebanking.R
import com.example.mobilebanking.ui.theme.selectItemColor
import com.example.mobilebanking.ui.theme.unSelectedItemColor


@Composable
fun RowScope.TabNavigatorItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val interactionSource = remember { MutableInteractionSource() }


    BottomNavigationItem(
        modifier = Modifier.background(color = Color.White),
        selectedContentColor = selectItemColor,
        unselectedContentColor = unSelectedItemColor,
        label = { Text(text = tab.options.title,
            fontFamily = FontFamily(Font(R.font.pnfont_regular)))},
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = tab.options.title) } },
        interactionSource = interactionSource,
        alwaysShowLabel = true
    )

}



