package com.andres.rangel.vitalyn.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andres.rangel.vitalyn.ui.components.BottomNavigationBar
import com.andres.rangel.vitalyn.ui.screens.HydrationScreen
import com.andres.rangel.vitalyn.ui.screens.LoginScreen
import com.andres.rangel.vitalyn.ui.screens.NutritionScreen
import com.andres.rangel.vitalyn.ui.screens.RestScreen
import com.andres.rangel.vitalyn.ui.screens.SettingsScreen
import com.andres.rangel.vitalyn.ui.screens.SportsScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    val bottomBarRoutes = listOf(
        Screen.Sports.route,
        Screen.Nutrition.route,
        Screen.Rest.route,
        Screen.Hydration.route,
        Screen.Settings.route
    )

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentDestination in bottomBarRoutes) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Sports.route,
            modifier = Modifier.fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            composable(route = Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(route = Screen.Sports.route) {
                SportsScreen(navController)
            }
            composable(route = Screen.Nutrition.route) {
                NutritionScreen(navController)
            }
            composable(route = Screen.Rest.route) {
                RestScreen(navController)
            }
            composable(route = Screen.Hydration.route) {
                HydrationScreen(navController)
            }
            composable(route = Screen.Settings.route) {
                SettingsScreen(navController)
            }
        }
    }
}