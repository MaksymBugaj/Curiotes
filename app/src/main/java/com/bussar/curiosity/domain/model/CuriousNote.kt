package com.bussar.curiosity.domain.model

import com.bussar.curiosity.data.db.entity.full.CuriousNoteFull
import java.time.ZonedDateTime

data class CuriousNote(
    val id: Long,
    val title: String?,
    val note: String?,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime?,
    val toCheck: Boolean,
    val isUploaded: Boolean = false,
    val links: List<CuriousNoteLink>?,
)