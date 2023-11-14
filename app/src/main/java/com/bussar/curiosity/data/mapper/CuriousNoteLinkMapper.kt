package com.bussar.curiosity.data.mapper

import com.bussar.curiosity.data.db.entity.CuriousNoteLinkEntity
import com.bussar.curiosity.data.mapper.base.DataMapper
import com.bussar.curiosity.data.mapper.base.DataMapperWithParam
import com.bussar.curiosity.domain.model.CuriousNoteLink
import javax.inject.Inject

class CuriousNoteLinkMapper @Inject constructor() : DataMapperWithParam<CuriousNoteLinkEntity, CuriousNoteLink, Long> {
    override fun mapToData(domainModel: CuriousNoteLink, param: Long): CuriousNoteLinkEntity {
        return CuriousNoteLinkEntity(
            id = domainModel.id,
            link = domainModel.link,
            curiousNoteId = param
        )
    }

    override fun mapToDomain(data: CuriousNoteLinkEntity): CuriousNoteLink {
        return CuriousNoteLink(
            data.id, data.link
        )
    }
}