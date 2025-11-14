package com.andres.rangel.vitalyn.sport.data.local

import android.media.Image
import androidx.compose.ui.graphics.vector.ImageVector
import com.andres.rangel.vitalyn.sport.R
import com.andres.rangel.vitalyn.sport.domain.model.Exercise
import com.andres.rangel.vitalyn.sport.domain.model.Routine
import com.andres.rangel.vitalyn.sport.domain.model.Streak
import javax.inject.Inject

class SportsLocalDataSource @Inject constructor() {

    fun getUserStreak(): Streak {
        return Streak(7, 10)
    }

    fun getRoutines(): List<Routine> {
        return listOf(
            Routine(
                title = "Tren inferior",
                category = "Cardio",
                exerciseList = listOf(
                    Exercise(
                        name = "ejercicio 1",
                        sets = 3,
                        reps = 10
                    ),
                    Exercise(
                        name = "ejercicio 2",
                        sets = 3,
                        reps = 10
                    )
                ),
                duration = 10,
                progress = 72,
                image = R.drawable.cycling
            )
        )
    }
}