package com.andres.rangel.vitalyn.sport.domain.usecase

import com.andres.rangel.vitalyn.sport.domain.model.Streak
import com.andres.rangel.vitalyn.sport.domain.model.WeeklyCalories
import com.andres.rangel.vitalyn.sport.domain.repository.SportsRepository
import javax.inject.Inject

class GetWeeklyCaloriesUseCase @Inject constructor(
    private val repository: SportsRepository
) {
    suspend operator fun invoke(): List<WeeklyCalories> {
        return repository.getWeeklyCalories()
    }
}