package com.andres.rangel.vitalyn.sport.data.local

import com.andres.rangel.vitalyn.sport.domain.model.Streak
import javax.inject.Inject

class SportsLocalDataSource @Inject constructor() {

    fun getUserStreak(): Streak {
        return Streak(7, 10)
    }
}