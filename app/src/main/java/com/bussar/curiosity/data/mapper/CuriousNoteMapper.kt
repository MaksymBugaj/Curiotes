package com.bussar.curiosity.data.mapper

import android.util.Log
import com.bussar.curiosity.data.db.entity.CuriousNoteEntity
import com.bussar.curiosity.data.db.entity.full.CuriousNoteFull
import com.bussar.curiosity.data.mapper.base.DataMapper
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.model.CuriousNoteLink
import javax.inject.Inject

class CuriousNoteMapper @Inject constructor(
    private val curiousNoteLinkMapper: CuriousNoteLinkMapper
) : DataMapper<CuriousNoteFull, CuriousNote> {
    override fun mapToData(domainModel: CuriousNote): CuriousNoteFull {
        return CuriousNoteFull(
            curiousNote = CuriousNoteEntity(
                id = domainModel.id,
                title = domainModel.title,
                note = domainModel.note,
                createdAt = domainModel.createdAt,
                modifiedAt = domainModel.modifiedAt,
                toCheck = domainModel.toCheck,
                isUploaded = domainModel.isUploaded
            ),
            links = domainModel.links?.map { link ->
                curiousNoteLinkMapper.mapToData(link, domainModel.id)
            }
        )
    }

    override fun mapToDomain(data: CuriousNoteFull): CuriousNote {
        return CuriousNote (
            id = data.curiousNote.id,
            title = data.curiousNote.title,
            note = data.curiousNote.note,
            createdAt = data.curiousNote.createdAt,
            modifiedAt = data.curiousNote.modifiedAt,
            toCheck = data.curiousNote.toCheck,
            isUploaded = data.curiousNote.isUploaded,
            links = data.links?.map { curiousNoteLinkEntity ->
                curiousNoteLinkMapper.mapToDomain(curiousNoteLinkEntity)
            }
        )
    }
}