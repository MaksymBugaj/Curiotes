package com.bussar.curiosity.data.repository

import android.util.Log
import com.bussar.curiosity.data.db.dao.CuriousNoteDao
import com.bussar.curiosity.data.db.dao.CuriousNoteLinkDao
import com.bussar.curiosity.data.db.entity.CuriousNoteEntity
import com.bussar.curiosity.data.db.entity.CuriousNoteLinkEntity
import com.bussar.curiosity.data.mapper.CuriousNoteLinkMapper
import com.bussar.curiosity.data.mapper.CuriousNoteMapper
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.repository.CuriousNoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CuriousNoteRepositoryImpl @Inject constructor(
    private val curiousNoteDao: CuriousNoteDao,
    private val curiousNoteLinkDao: CuriousNoteLinkDao,
    private val curiousNoteMapper: CuriousNoteMapper,
    private val curiousNoteLinkMapper: CuriousNoteLinkMapper
) : CuriousNoteRepository {

    override suspend fun saveNote(curiosNote: CuriousNote) {
        withContext(Dispatchers.IO){

            val id = async { curiousNoteDao.insertReturnId(curiousNoteMapper.mapToData(curiosNote))}
            val links = curiosNote.links?.map { curiousNoteLinkMapper.mapToData(it, id.await()) }
            links?.let { curiousNoteLinkDao.insert(it)}
        }
    }

    override fun selectNotes(): Flow<List<CuriousNote>> {
        Log.d("#NOPE", "select notes:")
        val test = curiousNoteDao.selectNotes().map { curiotes ->
            curiotes.map { curiote ->
                curiousNoteMapper.mapToDomain(curiote)
            }
        }
        Log.d("#NOPE","transformed: $test")
        return test
//        return curiousNoteDao.selectAll().map {
//            Log.d("#NOPE", "mapping curiotes:")
//            it.map { curiousNoteFull ->
//                Log.d("#NOPE", "mapped curiotes:")
//                val links = curiousNoteFull.links?.map {  link -> curiousNoteLinkMapper.mapToDomain(link)}
//                val curiousToDomain = curiousNoteMapper.mapToDomain(curiousNoteFull.curiousNote)
//                val returnValue  = if(!links.isNullOrEmpty())curiousToDomain.copy(links = links) else curiousToDomain
//                returnValue
//            }
//        }
    }
}