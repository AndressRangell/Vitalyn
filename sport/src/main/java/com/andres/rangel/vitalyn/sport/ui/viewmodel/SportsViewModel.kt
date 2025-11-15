package com.andres.rangel.vitalyn.sport.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andres.rangel.vitalyn.core.util.DataState
import com.andres.rangel.vitalyn.sport.domain.model.Routine
import com.andres.rangel.vitalyn.sport.domain.model.WeeklyCalories
import com.andres.rangel.vitalyn.sport.domain.usecase.GetRoutinesUseCase
import com.andres.rangel.vitalyn.sport.domain.usecase.GetUserStreakUseCase
import com.andres.rangel.vitalyn.sport.domain.usecase.GetWeeklyCaloriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val getUserStreakUseCase: GetUserStreakUseCase,
    private val getRoutinesUseCase: GetRoutinesUseCase,
    private val getWeeklyCaloriesUseCase: GetWeeklyCaloriesUseCase
) : ViewModel() {

    private val _streak = MutableStateFlow<DataState<Int>>(DataState.Loading)
    val streak: StateFlow<DataState<Int>> = _streak

    private val _routines = MutableStateFlow<DataState<List<Routine>>>(DataState.Loading)
    val routines: StateFlow<DataState<List<Routine>>> = _routines

    private val _weeklyCalories = MutableStateFlow<DataState<List<WeeklyCalories>>>(DataState.Loading)
    val weeklyCalories: StateFlow<DataState<List<WeeklyCalories>>> = _weeklyCalories


    init {
        loadUserStreak()
        loadRoutines()
        loadWeeklyCalories()
    }

    private fun loadUserStreak() {
        viewModelScope.launch {
            try {
                val streak = getUserStreakUseCase()
                _streak.value = DataState.Success(streak.currentStreak)
            } catch (e: Exception) {
                _streak.value = DataState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun loadRoutines() {
        viewModelScope.launch {
            try {
                val routines = getRoutinesUseCase()
                _routines.value = DataState.Success(routines)
            } catch (e: Exception) {
                _routines.value = DataState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun loadWeeklyCalories() {
        viewModelScope.launch {
            try {
                val weeklyCalories = getWeeklyCaloriesUseCase()
                _weeklyCalories.value = DataState.Success(weeklyCalories)
            } catch (e: Exception) {
                _weeklyCalories.value = DataState.Error(e.message ?: "Unknown error")
            }
        }
    }
}