package com.bussar.curiosity.domain.usecase

import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.repository.CuriousNoteRepository
import com.bussar.curiosity.domain.usecase.base.SuspendUseCase
import javax.inject.Inject

class UpdateCuriousNoteUseCase @Inject constructor(
    private val curiousNoteRepository: CuriousNoteRepository
) : SuspendUseCase<CuriousNote, Unit>() {

    override suspend fun execute(params: CuriousNote) {
        curiousNoteRepository.updateNote(params)
    }
}