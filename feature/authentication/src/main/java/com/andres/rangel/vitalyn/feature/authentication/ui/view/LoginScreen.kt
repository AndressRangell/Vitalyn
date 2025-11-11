package com.andres.rangel.vitalyn.feature.authentication.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(navigateToSports: () -> Unit) {
    Column {
        Text(text = "Login Screen")
        Button(
            onClick = { navigateToSports() }
        ) {
            Text(text = "Go to Sports Screen")
        }
    }
}