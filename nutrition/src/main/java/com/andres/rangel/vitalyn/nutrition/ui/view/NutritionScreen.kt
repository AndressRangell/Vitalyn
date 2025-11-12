package com.andres.rangel.vitalyn.nutrition.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NutritionScreen() {
    Column {
        Text(text = "Nutrition Screen")
        Button(
            onClick = { }
        ) {
            Text(text = "Go to Rest Screen")
        }
    }
}