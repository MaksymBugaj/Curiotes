package com.bussar.curiosity.domain.usecase

import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.repository.CuriousNoteRepository
import com.bussar.curiosity.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SelectCuriousNoteUseCase @Inject constructor(
    private val curiousNoteRepository: CuriousNoteRepository
) : FlowUseCase<Long, CuriousNote>() {

    override fun execute(params: Long): Flow<CuriousNote> {
        return curiousNoteRepository.selectNote(params)
    }
}