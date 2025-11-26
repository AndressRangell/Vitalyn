package com.andres.rangel.vitalyn.hydration.util.mapper

import com.andres.rangel.vitalyn.hydration.domain.model.UserHydrationLog
import com.andres.rangel.vitalyn.hydration.domain.model.UserWeeklyHydrationProgress
import com.andres.rangel.vitalyn.hydration.domain.util.enum.DrinkType
import com.andres.rangel.vitalyn.hydration.domain.util.enum.MonthDay
import com.andres.rangel.vitalyn.hydration.ui.model.UserHydrationLogUI
import com.andres.rangel.vitalyn.hydration.ui.model.UserWeeklyHydrationProgressUI
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun UserHydrationLog.toUI(): UserHydrationLogUI {
    val drinkType = DrinkType.entries.first { it.id == drinkTypeId }

    val localTime = Instant.ofEpochMilli(timestamp)
        .atZone(ZoneId.systemDefault())
        .toLocalTime()

    val formattedTime = localTime.format(
        DateTimeFormatter.ofPattern("hh:mm a")
    )

    return UserHydrationLogUI(
        drinkType = drinkType,
        amount = amount,
        formattedTime = formattedTime
    )
}

fun UserWeeklyHydrationProgress.toUI(): UserWeeklyHydrationProgressUI {
    val monthDay = MonthDay.entries.first { it.id == dayId }

    return UserWeeklyHydrationProgressUI(
        monthDay = monthDay,
        amount = amount,
        isStreakDay = isStreakDay
    )
}