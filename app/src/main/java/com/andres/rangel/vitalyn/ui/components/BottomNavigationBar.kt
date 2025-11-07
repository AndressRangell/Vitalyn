package com.andres.rangel.vitalyn.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andres.rangel.vitalyn.navigation.NavigationItem
import com.andres.rangel.vitalyn.navigation.Screen

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}

val navigationItems = listOf(
    NavigationItem(
        title = "Sports",
        icon = Icons.Default.Home,
        route = Screen.Sports.route
    ),
    NavigationItem(
        title = "Nutrition",
        icon = Icons.Default.Home,
        route = Screen.Nutrition.route
    ),
    NavigationItem(
        title = "Rest",
        icon = Icons.Default.Home,
        route = Screen.Rest.route
    ),
    NavigationItem(
        title = "Hydration",
        icon = Icons.Default.Home,
        route = Screen.Hydration.route
    ),
    NavigationItem(
        title = "Setting",
        icon = Icons.Default.Home,
        route = Screen.Settings.route
    )
)
