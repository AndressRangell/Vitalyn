package com.andres.rangel.vitalyn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andres.rangel.vitalyn.navigation.NavigationHost
import com.andres.rangel.vitalyn.ui.theme.VitalynTheme

class VitalynApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitalynTheme {
                NavigationHost()
            }
        }
    }
}