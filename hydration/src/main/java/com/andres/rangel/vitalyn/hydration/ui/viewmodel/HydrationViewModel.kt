package com.andres.rangel.vitalyn.hydration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andres.rangel.vitalyn.hydration.domain.model.UserHydrationLog
import com.andres.rangel.vitalyn.hydration.domain.model.UserWeeklyHydrationProgress
import com.andres.rangel.vitalyn.hydration.domain.util.enum.MonthDay
import com.andres.rangel.vitalyn.hydration.ui.model.UserHydrationLogUI
import com.andres.rangel.vitalyn.hydration.ui.model.UserWeeklyHydrationProgressUI
import com.andres.rangel.vitalyn.hydration.util.mapper.toUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class HydrationViewModel @Inject constructor() : ViewModel() {
    private val logs = arrayListOf(
        UserHydrationLog(
            id = 1,
            amount = 300,
            drinkTypeId = -1,
            timestamp = LocalDateTime.parse(
                "21/11/2025 11:30AM",
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mma")
            )
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli(),
        ),
        UserHydrationLog(
            id = 2,
            amount = 300,
            drinkTypeId = 3,
            timestamp = LocalDateTime.parse(
                "21/11/2025 08:06AM",
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mma")
            )
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli(),
        ),
        UserHydrationLog(
            id = 3,
            amount = 300,
            drinkTypeId = 5,
            timestamp = LocalDateTime.parse(
                "21/11/2025 10:31AM",
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mma")
            )
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli(),
        ),
        UserHydrationLog(
            id = 4,
            amount = 300,
            drinkTypeId = 4,
            timestamp = LocalDateTime.parse(
                "21/11/2025 11:01AM",
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mma")
            )
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli(),
        ),
        UserHydrationLog(
            id = 5,
            amount = 300,
            drinkTypeId = 2,
            timestamp = LocalDateTime.parse(
                "21/11/2025 03:00PM",
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mma")
            )
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli(),
        ),
        UserHydrationLog(
            id = 6,
            amount = 300,
            drinkTypeId = 3,
            timestamp = LocalDateTime.parse(
                "21/11/2025 08:00PM",
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mma")
            )
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli(),
        )
    )

    private val weeklyProgress = arrayListOf(
        UserWeeklyHydrationProgress(
            MonthDay.MONDAY.id,
            3000,
            true
        ),
        UserWeeklyHydrationProgress(
            MonthDay.TUESDAY.id,
            2500,
            true
        ),
        UserWeeklyHydrationProgress(
            MonthDay.WEDNESDAY.id,
            2000,
            false
        ),
        UserWeeklyHydrationProgress(
            MonthDay.THURSDAY.id,
            0,
            false
        ),
        UserWeeklyHydrationProgress(
            MonthDay.FRIDAY.id,
            3000,
            true
        ),
        UserWeeklyHydrationProgress(
            MonthDay.SATURDAY.id,
            0,
            false
        ),
        UserWeeklyHydrationProgress(
            MonthDay.SUNDAY.id,
            0,
            false
        )
    )


    private val _userStreak = MutableStateFlow(0)
    val userStreak: StateFlow<Int> = _userStreak.asStateFlow()

    private val _userDailyGoal = MutableStateFlow(0)
    val userDailyGoal: StateFlow<Int> = _userDailyGoal.asStateFlow()

    private val _userCurrentHydrationLvl = MutableStateFlow(0)
    val userCurrentHydrationLvl: StateFlow<Int> = _userCurrentHydrationLvl.asStateFlow()

    private val _userHydrationLogs = MutableStateFlow<List<UserHydrationLogUI>>(emptyList())
    val userHydrationLogs: StateFlow<List<UserHydrationLogUI>> = _userHydrationLogs.asStateFlow()

    private val _userWeeklyHydrationProgress =
        MutableStateFlow<List<UserWeeklyHydrationProgressUI>>(emptyList())
    val userWeeklyHydrationProgress: StateFlow<List<UserWeeklyHydrationProgressUI>> =
        _userWeeklyHydrationProgress.asStateFlow()

    init {
        loadUserStreak()
        loadUserDailyGoal()
        loadUserCurrentHydration()
        loadUserHydrationLogs()
        loadUserWeeklyProgress()
    }

    private fun loadUserWeeklyProgress() {
        viewModelScope.launch {
            _userWeeklyHydrationProgress.value = weeklyProgress.map { it.toUI() }
        }
    }

    private fun loadUserHydrationLogs() {
        viewModelScope.launch {
            _userHydrationLogs.value = logs.map { it.toUI() }
        }
    }

    private fun loadUserCurrentHydration() {
        viewModelScope.launch {
            _userCurrentHydrationLvl.value = 2000
        }
    }

    private fun loadUserDailyGoal() {
        viewModelScope.launch {
            _userDailyGoal.value = 2500
        }
    }

    private fun loadUserStreak() {
        viewModelScope.launch {
            _userStreak.value = 15
        }
    }

}