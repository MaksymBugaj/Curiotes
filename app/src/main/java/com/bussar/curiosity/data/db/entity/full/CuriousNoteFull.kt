package com.bussar.curiosity.data.db.entity.full

import androidx.room.Embedded
import androidx.room.Relation
import com.bussar.curiosity.data.db.entity.CuriousNoteEntity
import com.bussar.curiosity.data.db.entity.CuriousNoteLinkEntity

data class CuriousNoteFull(
    @Embedded
    val curiousNote: CuriousNoteEntity,
    @Relation(parentColumn = "id", entityColumn = "curiousNoteId")
    val links: List<CuriousNoteLinkEntity>?
)
