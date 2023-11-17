package com.bussar.curiosity.domain.usecase

import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.repository.CuriousNoteRepository
import com.bussar.curiosity.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SelectCuriousNotesUseCase @Inject constructor(
    private val curiousNoteRepository: CuriousNoteRepository
) : FlowUseCase<Unit, List<CuriousNote>>() {

    override fun execute(params: Unit): Flow<List<CuriousNote>> {
        return curiousNoteRepository.selectNotes()
    }
}