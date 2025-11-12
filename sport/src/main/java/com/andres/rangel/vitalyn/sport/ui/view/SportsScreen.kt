package com.andres.rangel.vitalyn.sport.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SportsScreen() {
    Column {
        Text(text = "Sports Screen")
        Button(
            onClick = { }
        ) {
            Text(text = "Go to Nutrition Screen")
        }
    }
}