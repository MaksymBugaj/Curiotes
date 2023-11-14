package com.bussar.curiosity.data.mapper

import com.bussar.curiosity.data.db.entity.CuriousNoteEntity
import com.bussar.curiosity.data.db.entity.full.CuriousNoteFull
import com.bussar.curiosity.data.mapper.base.DataMapper
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.model.CuriousNoteLink
import javax.inject.Inject

class CuriousNoteMapper @Inject constructor(
) : DataMapper<CuriousNoteEntity, CuriousNote> {
    override fun mapToData(domainModel: CuriousNote): CuriousNoteEntity {
        return CuriousNoteEntity(
                id = domainModel.id,
                title = domainModel.title,
                note = domainModel.note,
                createdAt = domainModel.createdAt,
                modifiedAt = domainModel.modifiedAt,
                toCheck = domainModel.toCheck,
                isUploaded = domainModel.isUploaded
            )
    }

    override fun mapToDomain(data: CuriousNoteEntity): CuriousNote {
        return CuriousNote (
            id = data.id,
            title = data.title,
            note = data.note,
            createdAt = data.createdAt,
            modifiedAt = data.modifiedAt,
            toCheck = data.toCheck,
            isUploaded = data.isUploaded,
            links = emptyList()
        )
    }
}