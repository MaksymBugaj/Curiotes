package com.bussar.curiosity.di

import com.bussar.curiosity.domain.usecase.SaveCuriousNoteUseCase
import com.bussar.curiosity.domain.usecase.SelectCuriousNotesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface UseCaseModuleDependencies {

    fun exposeSaveCuriousNoteUseCase(): SaveCuriousNoteUseCase

    fun exposeSelectCuriousNoteUseCase(): SelectCuriousNotesUseCase
}