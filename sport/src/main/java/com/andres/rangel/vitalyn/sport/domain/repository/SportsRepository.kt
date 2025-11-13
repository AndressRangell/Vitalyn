package com.andres.rangel.vitalyn.sport.domain.repository

import com.andres.rangel.vitalyn.sport.domain.model.Streak

interface SportsRepository {
    suspend fun getUserStreak(): Streak
}