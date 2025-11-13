package com.andres.rangel.vitalyn.sport.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andres.rangel.vitalyn.core.util.DataState
import com.andres.rangel.vitalyn.sport.domain.usecase.GetUserStreakUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val getUserStreakUseCase: GetUserStreakUseCase
) : ViewModel() {

    private val _streak = MutableStateFlow<DataState<Int>>(DataState.Loading)
    val streak: StateFlow<DataState<Int>> = _streak

    init {
        loadUserStreak()
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
}