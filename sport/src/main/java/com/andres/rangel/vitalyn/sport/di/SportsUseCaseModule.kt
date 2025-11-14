package com.andres.rangel.vitalyn.sport.di

import com.andres.rangel.vitalyn.sport.domain.repository.SportsRepository
import com.andres.rangel.vitalyn.sport.domain.usecase.GetRoutinesUseCase
import com.andres.rangel.vitalyn.sport.domain.usecase.GetUserStreakUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SportsUseCaseModule {

    @Provides
    fun provideGetUserStreakUseCase(
        repository: SportsRepository
    ): GetUserStreakUseCase = GetUserStreakUseCase(repository)

    @Provides
    fun provideGetRoutineUseCase(
        repository: SportsRepository
    ): GetRoutinesUseCase = GetRoutinesUseCase(repository)
}