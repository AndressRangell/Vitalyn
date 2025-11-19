package com.andres.rangel.vitalyn.sport.utils

import com.andres.rangel.vitalyn.sport.domain.model.CalendarDay
import java.time.LocalDate

object CalendarUtils {
    fun getWeekMondayToSunday(date: LocalDate): List<CalendarDay> {
        val dayOfWeek = date.dayOfWeek.value

        val startOfWeek = date.minusDays((dayOfWeek - 1).toLong())
        return (0..6).map { offset ->
            val current = startOfWeek.plusDays(offset.toLong())
            CalendarDay(
                day = current.dayOfMonth,
                month = current.monthValue,
                year = current.year,
                isToday = current == LocalDate.now(),
                isFromCurrentMonth = current.month == date.month
            )
        }
    }

    fun getCalendarTitle(week: List<CalendarDay>): String {
        val months = week.map { it.month }.toSet().toList().sorted()
        val years = week.map { it.year }.toSet().toList().sorted()

        val monthNames = listOf(
            "", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        )
        val monthNamesShort = listOf(
            "", "Ene", "Feb", "Mar", "Abr", "May", "Jun",
            "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
        )

        return when {
            months.size == 1 && years.size == 1 -> {
                "${monthNames[months[0]]} ${years[0]}"
            }
            months.size == 2 && years.size == 1 -> {
                "${monthNamesShort[months[0]]} / ${monthNamesShort[months[1]]} ${years[0]}"
            }
            else -> {
                "${monthNamesShort[months[1]]} ${years[0]} / ${monthNamesShort[months[0]]} ${years[1]}"
            }
        }
    }
}