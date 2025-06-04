package com.fiap.aquavita.composable.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fiap.aquavita.ui.theme.AquaBlue

sealed class Tab(val route: String, val icon: ImageVector, val label: String) {
    object Home  : Tab("home",  Icons.Default.Home,  "Início")
    object Educa : Tab("dicas", Icons.Default.Check, "Dicas")
    object Mapa  : Tab("map",   Icons.Default.Place, "Mapa de Ajuda")
}

@Composable
fun AquaBottomBar(nav: NavController) {
    val tabs = listOf(Tab.Home, Tab.Educa, Tab.Mapa)
    val dest by nav.currentBackStackEntryAsState()
    val current = dest?.destination?.route

    NavigationBar {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = current == tab.route,
                onClick = { nav.navigate(tab.route) { launchSingleTop = true } },
                icon = { Icon(tab.icon, null) },
                label = { Text(tab.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AquaBlue,
                    selectedTextColor = AquaBlue
                )
            )
        }
    }
}