package com.andres.rangel.vitalyn.hydration.ui.model

import com.andres.rangel.vitalyn.hydration.domain.util.enum.MonthDay

data class UserWeeklyHydrationProgressUI(
    val monthDay: MonthDay,
    val amount: Int,
    val isStreakDay: Boolean
)
