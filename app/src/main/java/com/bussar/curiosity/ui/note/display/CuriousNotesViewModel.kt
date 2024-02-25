package com.bussar.curiosity.ui.note.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.usecase.SelectCuriousNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CuriousNotesViewModel @Inject constructor(
    private val selectCuriousNotesUseCase: SelectCuriousNotesUseCase,
): ViewModel(){
    private val _notes = MutableStateFlow<List<CuriousNote>>(emptyList())
    val notes: StateFlow<List<CuriousNote>> = _notes

    init {
        selectAllCuriousNotes()
    }

    //Region SELECT
    private fun selectAllCuriousNotes() {
        viewModelScope.launch {
            selectCuriousNotesUseCase.execute(Unit).onEach { curiousNotes ->
                _notes.value = curiousNotes
            }.collect()
        }
    }
    //end Region SELECT
}