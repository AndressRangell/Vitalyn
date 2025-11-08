package com.andres.rangel.vitalyn.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.andres.rangel.vitalyn.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold),

    // Italic
    Font(R.font.montserrat_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.montserrat_bolditalic, FontWeight.Bold, FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography().run {
    copy(
        displayLarge = displayLarge.copy(fontFamily = Montserrat),
        displayMedium = displayMedium.copy(fontFamily = Montserrat),
        displaySmall = displaySmall.copy(fontFamily = Montserrat),
        headlineLarge = headlineLarge.copy(fontFamily = Montserrat),
        headlineMedium = headlineMedium.copy(fontFamily = Montserrat),
        headlineSmall = headlineSmall.copy(fontFamily = Montserrat),
        titleLarge = titleLarge.copy(fontFamily = Montserrat),
        titleMedium = titleMedium.copy(fontFamily = Montserrat),
        titleSmall = titleSmall.copy(fontFamily = Montserrat),
        bodyLarge = bodyLarge.copy(fontFamily = Montserrat),
        bodyMedium = bodyMedium.copy(fontFamily = Montserrat),
        bodySmall = bodySmall.copy(fontFamily = Montserrat),
        labelLarge = labelLarge.copy(fontFamily = Montserrat),
        labelMedium = labelMedium.copy(fontFamily = Montserrat),
        labelSmall = labelSmall.copy(fontFamily = Montserrat)
    )
}