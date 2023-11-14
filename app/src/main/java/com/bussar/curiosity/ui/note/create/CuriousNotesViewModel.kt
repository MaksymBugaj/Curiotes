package com.bussar.curiosity.ui.note.create

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.model.CuriousNoteLink
import com.bussar.curiosity.domain.usecase.SaveCuriousNoteUseCase
import com.bussar.curiosity.domain.usecase.SelectCuriousNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class CuriousNotesViewModel @Inject constructor(
    private val selectCuriousNotesUseCase: SelectCuriousNotesUseCase,
    private val saveCuriousNoteUseCase: SaveCuriousNoteUseCase
): ViewModel() {

    private val _isSheetOpen = MutableStateFlow(false)
    val isSheetOpen: StateFlow<Boolean> = _isSheetOpen

    private val _noteTitle = MutableStateFlow("")
    val noteTitle: StateFlow<String> = _noteTitle

    private val _noteDescription = MutableStateFlow("")
    val noteDescription: StateFlow<String> = _noteDescription

    private val _noteLink = MutableStateFlow("")
    val noteLink: StateFlow<String> = _noteLink

    private val _needsDetailedExplanation = MutableStateFlow(false)
    val needsDetailedExplanation :StateFlow<Boolean> = _needsDetailedExplanation

    //todo use combine maybe?
    private val _showSavingError = MutableStateFlow(false)
    val showSavingError: StateFlow<Boolean> = _showSavingError

    private val _notes = MutableStateFlow<List<CuriousNote>>(emptyList())
    val notes: StateFlow<List<CuriousNote>> = _notes

    init {
        selectAllCuriousNotes()
    }

    //region SET
    fun setSheetValue(value: Boolean) {
        _isSheetOpen.value = value
    }

    fun setNoteTitle(value: String) {
        _noteTitle.value = value
    }

    fun setNoteDescription(value: String) {
        _noteDescription.value = value
    }

    fun setNoteLink(value: String) {
        _noteLink.value = value
    }

    fun setDetailedExplanation(value: Boolean) {
        _needsDetailedExplanation.value = value
    }

    private fun setShowSavingErrorValue(value: Boolean) {
        _showSavingError.value = value
    }

    // end Region SET

    //Region SELECT
    private fun selectAllCuriousNotes(){
        Log.d("#NOPE", "Getting curiotes:")
        viewModelScope.launch {
            selectCuriousNotesUseCase.execute(Unit).onEach { curiousNotes ->
                Log.d("#NOPE", "setting curiotes: $curiousNotes")
                _notes.value = curiousNotes
            }.collect()
        }
    }
    //end Region SELECT
    //Region SAVE

    fun saveClicked() {
        if(validate()){
            save()
        } else {
            _showSavingError.value = true
        }
    }

    private fun validate(): Boolean{
        return _noteTitle.value.isNotEmpty() ||
                _noteDescription.value.isNotEmpty() ||
                noteLink.value.isNotEmpty()
    }

    private fun save(){
        val links = CuriousNoteLink(
            id = 0,
            link = _noteLink.value
        )
        val curiousNote = CuriousNote(
            id = 0,
            title = _noteTitle.value,
            note = _noteDescription.value,
            createdAt = ZonedDateTime.now(),
            modifiedAt = null,
            links = listOf(links),
            toCheck = _needsDetailedExplanation.value
        )
        viewModelScope.launch {
            saveCuriousNoteUseCase.execute(curiousNote)
        }
        setSheetValue(false)
        setShowSavingErrorValue(false)
        selectAllCuriousNotes()
        clearFields()
    }

    //END REGION SAVE

    private fun clearFields(){
        setNoteTitle("")
        setNoteDescription("")
        setNoteLink("")
    }
}