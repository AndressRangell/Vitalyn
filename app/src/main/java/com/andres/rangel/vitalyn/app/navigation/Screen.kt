package com.andres.rangel.vitalyn.app.navigation

sealed class Screen(val route: String) {
    // Authentication Screens
    data object Splash: Screen("splash_screen")
    data object Login: Screen("login_screen")
    data object Register: Screen("register_screen")
    data object ForgotPassword: Screen("forgot_password_screen")

    // Main App Screens
    data object BottomBar: Screen("bottom_bar")
    data object Sports: Screen("sports_screen")
    data object Nutrition: Screen("nutrition_screen")
    data object Rest: Screen("rest_screen")
    data object Hydration: Screen("hydration_screen")

    // Settings Screens
    data object Profile: Screen("profile_screen")
    data object Settings: Screen("settings_screen")
}