package com.andres.rangel.vitalyn.nutrition.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.andres.rangel.vitalyn.app.navigation.Screen

@Composable
fun NutritionScreen(navController: NavHostController) {
    Column {
        Text(text = "Nutrition Screen")
        Button(
            onClick = { navController.navigate(Screen.Rest.route) }
        ) {
            Text(text = "Go to Rest Screen")
        }
    }
}