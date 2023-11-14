package com.bussar.curiosity.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.bussar.curiosity.data.db.entity.CuriousNoteEntity
import com.bussar.curiosity.data.db.entity.full.CuriousNoteFull
import kotlinx.coroutines.flow.Flow

@Dao
interface CuriousNoteDao : BaseDao<CuriousNoteEntity> {

    @Transaction
    @Query("select * from curious_note cn inner join curious_note_link as cnl on cn.id == cnl.curiousNoteId")
    fun selectAll(): Flow<List<CuriousNoteFull>>

    @Query("select * from curious_note")
    fun selectNotes(): Flow<List<CuriousNoteEntity>>

}