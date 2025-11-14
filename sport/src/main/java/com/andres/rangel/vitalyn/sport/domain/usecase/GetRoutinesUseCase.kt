package com.andres.rangel.vitalyn.sport.domain.usecase

import com.andres.rangel.vitalyn.sport.domain.model.Routine
import com.andres.rangel.vitalyn.sport.domain.repository.SportsRepository
import javax.inject.Inject

class GetRoutinesUseCase @Inject constructor(
    private val repository: SportsRepository
) {
    suspend operator fun invoke(): List<Routine> {
        return repository.getRoutines()
    }
}