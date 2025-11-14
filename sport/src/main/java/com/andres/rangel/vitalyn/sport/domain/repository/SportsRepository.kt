package com.andres.rangel.vitalyn.sport.domain.repository

import com.andres.rangel.vitalyn.sport.domain.model.Routine
import com.andres.rangel.vitalyn.sport.domain.model.Streak

interface SportsRepository {
    suspend fun getUserStreak(): Streak

    suspend fun getRoutines(): List<Routine>
}