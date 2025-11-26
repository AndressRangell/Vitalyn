package com.andres.rangel.vitalyn.hydration.domain.model

data class UserWeeklyHydrationProgress(
    val dayId: Int,
    val amount: Int,
    val isStreakDay: Boolean
)
