package com.andres.rangel.vitalyn.sport.domain.model

import java.time.LocalDate

data class CalendarDay(
    val day: Int,
    val month: Int,
    val year: Int,
    val isToday: Boolean,
    val isFromCurrentMonth: Boolean
)