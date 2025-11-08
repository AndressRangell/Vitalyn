package com.andres.rangel.vitalyn.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun RestScreen(navController: NavHostController) {
    Column {
        Text(text = "Rest Screen")
        Button(
            onClick = { navController.popBackStack() }
        ) {
            Text(text = "Go to previous Screen")
        }
    }
}