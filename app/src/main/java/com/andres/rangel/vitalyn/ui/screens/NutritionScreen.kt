package com.andres.rangel.vitalyn.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.andres.rangel.vitalyn.navigation.Screen

@Composable
fun NutritionScreen(navController: NavHostController) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {
            Text(text = "Nutrition Screen")
            Button(
                onClick = { navController.navigate(Screen.Rest.route) }
            ) {
                Text(text = "Go to Rest Screen")
            }
        }
    }
}