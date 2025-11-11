package com.andres.rangel.vitalyn.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.andres.rangel.vitalyn.app.navigation.NavigationHost
import com.andres.rangel.vitalyn.core.ui.theme.VitalynTheme
import com.andres.rangel.vitalyn.app.ui.splash.SplashAnimationType
import com.andres.rangel.vitalyn.app.ui.splash.setAnimation

class MainActivity : ComponentActivity() {
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