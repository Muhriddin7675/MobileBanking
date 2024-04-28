//package com.example.mobilebanking.util
//
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material.Icon
//import androidx.compose.material.Text
//import androidx.compose.material3.Icon
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
//import cafe.adriel.voyager.navigator.tab.Tab
//
//
//@Composable
//fun RowScope.TabNavigatorItem(tab: Tab) {
//    val tabNavigator = LocalTabNavigator.current
//
//    BottomNavigationItem(
//        selected = tabNavigator.current == tab,
//        onClick = { tabNavigator.current = tab },
//        label = {
//            Text(
//                text = tab.options.title,
//                modifier = Modifier.padding(vertical = 8.dp),
//                fontSize = 12.sp
//            )
//        },
//        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
//    )
//}
//
