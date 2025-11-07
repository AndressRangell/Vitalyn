package com.andres.rangel.vitalyn.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.andres.rangel.vitalyn.navigation.Screen

@Composable
fun LoginScreen(navController: NavHostController) {
    Column {
        Text(text = "Login Screen")
        Button(
            onClick = { navController.navigate(Screen.Sports.route) }
        ) {
            Text(text = "Go to Sports Screen")
        }
    }
}