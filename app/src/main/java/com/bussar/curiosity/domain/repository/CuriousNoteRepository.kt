package com.bussar.curiosity.domain.repository

import com.bussar.curiosity.domain.model.CuriousNote
import kotlinx.coroutines.flow.Flow

interface CuriousNoteRepository {

    suspend fun saveNote(curiosNote: CuriousNote)

    fun selectNotes(): Flow<List<CuriousNote>>

    suspend fun updateNote(curiousNote: CuriousNote)
}