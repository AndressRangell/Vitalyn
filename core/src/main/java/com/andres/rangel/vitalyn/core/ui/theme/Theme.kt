package com.andres.rangel.vitalyn.core.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    /*** Used by default by Material Design ***/
    background = BlackBackground,
    onBackground = GrayLight,

    onSurface = GreenPastel,

    primary = GrayLight,
    onPrimary = BlackBackground,

    primaryContainer = GreenPastel,
    onPrimaryContainer = Black,

    secondary = GrayDark,
    onSecondary = White,

    surfaceVariant = Gray,
    onSurfaceVariant = GrayLight,

    outline = GrayLight,

    /*** Properties for customizing components ***/
    error = RedLight,
    onError = RedDark,
    errorContainer = BlackBackground,
    onErrorContainer = Red,

    tertiary = RedVibrant
)

@Composable
fun VitalynTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window

        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}