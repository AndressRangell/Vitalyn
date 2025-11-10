package com.andres.rangel.vitalyn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.andres.rangel.vitalyn.navigation.NavigationHost
import com.andres.rangel.vitalyn.ui.theme.VitalynTheme
import com.andres.rangel.vitalyn.utils.SplashAnimationType
import com.andres.rangel.vitalyn.utils.setAnimation

class VitalynApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setAnimation(SplashAnimationType.SCALE_FADE)
        enableEdgeToEdge()
        setContent {
            VitalynTheme {
                NavigationHost()
            }
        }
    }
}