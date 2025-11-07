package com.andres.rangel.vitalyn.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andres.rangel.vitalyn.ui.screens.HydrationScreen
import com.andres.rangel.vitalyn.ui.screens.NutritionScreen
import com.andres.rangel.vitalyn.ui.screens.RestScreen
import com.andres.rangel.vitalyn.ui.screens.SettingsScreen
import com.andres.rangel.vitalyn.ui.screens.SportsScreen

@Composable
fun NavigationHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Sports.route
    ) {
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