package com.bussar.curiosity.domain.usecase

import android.util.Log
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.repository.CuriousNoteRepository
import com.bussar.curiosity.domain.usecase.base.FlowUseCase
import com.bussar.curiosity.domain.usecase.base.SuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SelectCuriousNotesUseCase @Inject constructor(
    private val curiousNoteRepository: CuriousNoteRepository
) : FlowUseCase<Unit, List<CuriousNote>>() {

    override fun execute(params: Unit): Flow<List<CuriousNote>> {
        val notes =  curiousNoteRepository.selectNotes()
        Log.d("#NOPE","noes in usecase: $notes")
       return notes
    }
}