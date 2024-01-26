package com.bussar.curiosity.data.repository

import android.util.Log
import com.bussar.curiosity.data.db.dao.CuriousNoteDao
import com.bussar.curiosity.data.db.dao.CuriousNoteLinkDao
import com.bussar.curiosity.data.mapper.CuriousNoteLinkMapper
import com.bussar.curiosity.data.mapper.CuriousNoteMapper
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.repository.CuriousNoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CuriousNoteRepositoryImpl @Inject constructor(
    private val curiousNoteDao: CuriousNoteDao,
    private val curiousNoteLinkDao: CuriousNoteLinkDao,
    private val curiousNoteMapper: CuriousNoteMapper,
    private val curiousNoteLinkMapper: CuriousNoteLinkMapper
) : CuriousNoteRepository {

    override suspend fun saveNote(curiosNote: CuriousNote) {
        withContext(Dispatchers.IO){
            try {
                val id =
                    async { curiousNoteDao.insertReturnId(curiousNoteMapper.mapToData(curiosNote).curiousNote) }
                val links =
                    curiosNote.links?.map { curiousNoteLinkMapper.mapToData(it, id.await()) }
                links?.let {
                    curiousNoteLinkDao.insert(it) }
            } catch (e: Exception) {
                //todo delete
                Log.d("#NOPE","Exception in saving: ${e.message}")
            }
        }
    }

    override fun selectNotes(): Flow<List<CuriousNote>> {
        return curiousNoteDao.selectNotes().map { curiotes ->
            curiotes.map { curiote ->
                curiousNoteMapper.mapToDomain(curiote)
            }
        }
    }

    override suspend fun updateNote(curiousNote: CuriousNote) {
        withContext(Dispatchers.IO) {
            curiousNoteDao.update(curiousNoteMapper.mapToData(curiousNote).curiousNote)
            val links = curiousNote.links?.map { curiousNoteLinkMapper.mapToData(it, curiousNote.id) }
            links?.let { curiousNoteLinkDao.insert(it)}
        }
    }
}