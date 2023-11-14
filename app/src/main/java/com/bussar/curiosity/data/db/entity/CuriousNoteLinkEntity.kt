package com.bussar.curiosity.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bussar.curiosity.domain.model.CuriousNoteLink

@Entity(
    tableName = "curious_note_link",
    foreignKeys = [
        ForeignKey(
            entity = CuriousNoteEntity::class,
            childColumns = ["curiousNoteId"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ],
    indices = [Index(value = ["id"])]
)
data class CuriousNoteLinkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val link: String,
    val curiousNoteId: Long,
)