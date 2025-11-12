package com.andres.rangel.vitalyn.rest.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun RestScreen() {
    Column {
        Text(text = "Rest Screen")
        Button(
            onClick = { }
        ) {
            Text(text = "Go to previous Screen")
        }
    }
}