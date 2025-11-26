package com.andres.rangel.vitalyn.hydration.util.extension

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.andres.rangel.vitalyn.hydration.R
import com.andres.rangel.vitalyn.hydration.domain.util.enum.DrinkType
import com.andres.rangel.vitalyn.hydration.domain.util.enum.MonthDay
import com.andres.rangel.vitalyn.hydration.ui.model.UserWeeklyHydrationProgressUI

fun DrinkType.getIcon(): Int = when (this) {
    DrinkType.WATER_GLASS -> R.drawable.water_glass
    DrinkType.WATER_BOTTLE -> R.drawable.water_bottle
    DrinkType.COFFEE -> R.drawable.coffee
    else -> R.drawable.empty_bottle
}

@Composable
fun DrinkType.getLabel(): String = when (this) {
    DrinkType.WATER_GLASS -> stringResource(R.string.hydration_log_type_water_glass)
    DrinkType.WATER_BOTTLE -> stringResource(R.string.hydration_log_type_water_bottle)
    DrinkType.COFFEE -> stringResource(R.string.hydration_log_type_coffee)
    DrinkType.TEA -> stringResource(R.string.hydration_log_type_tea)
    DrinkType.JUICE -> stringResource(R.string.hydration_log_type_juice)
    else -> stringResource(R.string.hydration_log_type_undefined)
}

@Composable
fun UserWeeklyHydrationProgressUI.getColor(): Color =
    if (isStreakDay) MaterialTheme.colorScheme.primaryContainer
    else MaterialTheme.colorScheme.primary

@Composable
fun MonthDay.getInitialLetter(): Char = when (this) {
    MonthDay.MONDAY -> stringResource(R.string.weekly_progress_initial_letter_day_monday).first()
    MonthDay.TUESDAY -> stringResource(R.string.weekly_progress_initial_letter_day_tuesday).first()
    MonthDay.WEDNESDAY -> stringResource(R.string.weekly_progress_initial_letter_day_wednesday).first()
    MonthDay.THURSDAY -> stringResource(R.string.weekly_progress_initial_letter_day_thursday).first()
    MonthDay.FRIDAY -> stringResource(R.string.weekly_progress_initial_letter_day_friday).first()
    MonthDay.SATURDAY -> stringResource(R.string.weekly_progress_initial_letter_day_saturday).first()
    MonthDay.SUNDAY -> stringResource(R.string.weekly_progress_initial_letter_day_sunday).first()
    MonthDay.UNDEFINED -> stringResource(R.string.weekly_progress_initial_letter_day_undefined).first()
}