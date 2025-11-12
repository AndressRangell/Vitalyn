package com.andres.rangel.vitalyn.app.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andres.rangel.vitalyn.app.ui.components.BottomNavigationBar
import com.andres.rangel.vitalyn.authentication.ui.view.LoginScreen
import com.andres.rangel.vitalyn.hydration.ui.view.HydrationScreen
import com.andres.rangel.vitalyn.nutrition.ui.view.NutritionScreen
import com.andres.rangel.vitalyn.rest.ui.view.RestScreen
import com.andres.rangel.vitalyn.setting.ui.view.SettingsScreen
import com.andres.rangel.vitalyn.sport.ui.view.SportsScreen

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
            startDestination = Screen.Sports.route
        ) {
            composable(route = Screen.Login.route) {
                LoginScreen(navigateToSports = { navController.navigate(Screen.Sports.route) })
            }
            composable(route = Screen.Sports.route) {
                SportsScreen()
            }
            composable(route = Screen.Nutrition.route) {
                NutritionScreen()
            }
            composable(route = Screen.Rest.route) {
                RestScreen()
            }
            composable(route = Screen.Hydration.route) {
                HydrationScreen()
            }
            composable(route = Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}