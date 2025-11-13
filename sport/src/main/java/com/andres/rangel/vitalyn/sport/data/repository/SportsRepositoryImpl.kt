package com.andres.rangel.vitalyn.sport.data.repository

import com.andres.rangel.vitalyn.sport.data.local.SportsLocalDataSource
import com.andres.rangel.vitalyn.sport.domain.model.Routine
import com.andres.rangel.vitalyn.sport.domain.model.Streak
import com.andres.rangel.vitalyn.sport.domain.repository.SportsRepository
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val localDataSource: SportsLocalDataSource
) : SportsRepository {

    override suspend fun getUserStreak(): Streak {
        return localDataSource.getUserStreak()
    }

    override suspend fun getRoutines(): List<Routine> {
        return localDataSource.getRoutines()
    }
}