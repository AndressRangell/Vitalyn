package com.andres.rangel.vitalyn.sport.domain.usecase

import com.andres.rangel.vitalyn.sport.domain.model.Streak
import com.andres.rangel.vitalyn.sport.domain.repository.SportsRepository
import javax.inject.Inject

class GetUserStreakUseCase @Inject constructor(
    private val repository: SportsRepository
) {
    suspend operator fun invoke(): Streak {
        return repository.getUserStreak()
    }
}