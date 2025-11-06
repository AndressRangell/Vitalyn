package com.andres.rangel.vitalyn.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun RestScreen(navController: NavHostController) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {
            Text(text = "Rest Screen")
            Button(
                onClick = { navController.popBackStack() }
            ) {
                Text(text = "Go to previous Screen")
            }
        }
    }
}