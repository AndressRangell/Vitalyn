package com.andres.rangel.vitalyn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.andres.rangel.vitalyn.navigation.NavigationHost
import com.andres.rangel.vitalyn.ui.theme.VitalynTheme

class VitalynApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalynTheme {
                val navController = rememberNavController()
                NavigationHost(navController)
            }
        }
    }
}