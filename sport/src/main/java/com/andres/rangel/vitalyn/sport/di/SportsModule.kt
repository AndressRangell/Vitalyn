package com.andres.rangel.vitalyn.sport.di

import com.andres.rangel.vitalyn.sport.data.local.SportsLocalDataSource
import com.andres.rangel.vitalyn.sport.data.repository.SportsRepositoryImpl
import com.andres.rangel.vitalyn.sport.domain.repository.SportsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SportsModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(): SportsLocalDataSource = SportsLocalDataSource()

    @Provides
    @Singleton
    fun provideSportsRepository(
        localDataSource: SportsLocalDataSource
    ): SportsRepository = SportsRepositoryImpl(localDataSource)
}