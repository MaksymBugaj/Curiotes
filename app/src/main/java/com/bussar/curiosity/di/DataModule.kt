package com.bussar.curiosity.di

import com.bussar.curiosity.data.repository.CuriousNoteRepositoryImpl
import com.bussar.curiosity.domain.repository.CuriousNoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindCuriousRepository(
        curiousNoteRepositoryImpl: CuriousNoteRepositoryImpl
    ): CuriousNoteRepository
}