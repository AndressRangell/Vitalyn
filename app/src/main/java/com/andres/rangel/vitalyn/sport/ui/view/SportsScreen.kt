package com.andres.rangel.vitalyn.sport.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.andres.rangel.vitalyn.app.navigation.Screen

@Composable
fun SportsScreen(navController: NavHostController) {
    Column {
        Text(text = "Sports Screen")
        Button(
            onClick = { navController.navigate(Screen.Nutrition.route) }
        ) {
            Text(text = "Go to Nutrition Screen")
        }
    }
}