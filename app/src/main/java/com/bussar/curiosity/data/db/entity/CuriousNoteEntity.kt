package com.bussar.curiosity.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bussar.curiosity.domain.model.CuriousNote
import java.time.ZonedDateTime

@Entity(tableName = "curious_note")
data class CuriousNoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String?,
    val note: String?,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime?,
    val toCheck: Boolean,
    val isUploaded: Boolean = false,
)
