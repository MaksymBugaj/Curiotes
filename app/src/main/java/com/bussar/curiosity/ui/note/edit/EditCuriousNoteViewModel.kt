package com.bussar.curiosity.ui.note.edit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bussar.curiosity.domain.model.CuriousNote
import com.bussar.curiosity.domain.model.CuriousNoteLink
import com.bussar.curiosity.domain.usecase.SaveCuriousNoteUseCase
import com.bussar.curiosity.domain.usecase.SelectCuriousNoteUseCase
import com.bussar.curiosity.domain.usecase.UpdateCuriousNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class EditCuriousNoteViewModel @Inject  constructor(
    private val saveCuriousNoteUseCase: SaveCuriousNoteUseCase,
    private val updateCuriousNoteUseCase: UpdateCuriousNoteUseCase,
    private val selectCuriousNoteUseCase: SelectCuriousNoteUseCase
) : ViewModel(){
    private val _noteTitle = MutableStateFlow("")
    val noteTitle: StateFlow<String> = _noteTitle

    private val _noteDescription = MutableStateFlow("")
    val noteDescription: StateFlow<String> = _noteDescription

    private val _noteLink = MutableStateFlow("")
    val noteLink: StateFlow<String> = _noteLink

    private val _needsDetailedExplanation = MutableStateFlow(false)
    val needsDetailedExplanation: StateFlow<Boolean> = _needsDetailedExplanation

    //todo use combine maybe?
    private val _showSavingError = MutableStateFlow(false)
    val showSavingError: StateFlow<Boolean> = _showSavingError

    private val _isUpdating = MutableStateFlow(false)
    private var updatingCuriote: CuriousNote? = null

    val showCreateErrorTest = combine(
        _noteDescription, _noteLink, _noteTitle, _showSavingError
    ) { p1, p2,p3, p4 ->
        if(!showSavingError.value) false
        else p1.isEmpty() && p2.isEmpty() && p3.isEmpty() && p4
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(300), false)

    val enableSaveButton = combine(
        _noteDescription, _noteLink, _noteTitle
    ) { p1, p2, p3 ->
        p1.isNotEmpty() || p2.isNotEmpty() || p3.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(300),false)

    //region RETRIEVE
    fun retrieveData(id: Long){
        viewModelScope.launch {
            selectCuriousNoteUseCase.execute(id).onEach {
                editCuriote(it)
            }.collect()
        }
    }
    //region SET

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

    fun setIsUpdating(value: Boolean) {
        _isUpdating.value = value
    }

    fun setUpdatingCuriote(value: CuriousNote){
        updatingCuriote = value
    }
    // end Region SET

    //Region SAVE

    fun saveClicked() {
        if (validate()) {
            if(_isUpdating.value){
                updateCuriote(updatingCuriote!!)
            } else {
                save()
            }

        } else {
            _showSavingError.value = true
        }
    }

    private fun validate(): Boolean {
        return _noteTitle.value.isNotEmpty() ||
                _noteDescription.value.isNotEmpty() ||
                noteLink.value.isNotEmpty()
    }

    private fun save() {
        val links = if(_noteLink.value.isNotBlank()) {
            listOf(
                CuriousNoteLink(
                id = 0,
                link = _noteLink.value
            )
            )
        } else emptyList<CuriousNoteLink>()
        //todo delete
        Log.d("#NOPE","link to save: $links")
        val curiousNote = CuriousNote(
            id = 0,
            title = _noteTitle.value,
            note = _noteDescription.value,
            createdAt = ZonedDateTime.now(),
            modifiedAt = ZonedDateTime.now(),
            links = links,
            toCheck = _needsDetailedExplanation.value
        )
        viewModelScope.launch {
            saveCuriousNoteUseCase.execute(curiousNote)
        }
        clearFields()
    }

    private fun updateCuriote(curiousNote: CuriousNote){
        val links = if(_noteLink.value.isNotEmpty() && curiousNote.links.isNullOrEmpty()){
            listOf(
                CuriousNoteLink(
                    id = 0,
                    link = _noteLink.value
                )
            )
        } else if (!curiousNote.links.isNullOrEmpty()){
            listOf(
                CuriousNoteLink(
                    id = curiousNote.links.first().id,
                    link = _noteLink.value
                )
            )
        } else {
            emptyList()
        }

        val note = CuriousNote(
            id = curiousNote.id,
            title = _noteTitle.value,
            note = _noteDescription.value,
            createdAt = curiousNote.createdAt,
            modifiedAt = ZonedDateTime.now(),
            links = links,
            toCheck = _needsDetailedExplanation.value
        )
        viewModelScope.launch {
            updateCuriousNoteUseCase.execute(note)
        }
        clearFields()
    }

    //END REGION SAVE

    private fun clearFields() {
        setNoteTitle("")
        setNoteDescription("")
        setNoteLink("")
        setIsUpdating(false)
        setShowSavingErrorValue(false)
    }

    // REGION EDIT CURIOTE

    fun editCuriote(curiousNote: CuriousNote) {
        setUpdatingCuriote(curiousNote)
        setIsUpdating(true)
        setDetailedExplanation(curiousNote.toCheck)
        curiousNote.title?.let { title -> setNoteTitle(title) }
        curiousNote.note?.let { note -> setNoteDescription(note) }
        curiousNote.links?.let { links -> links.firstOrNull()?.let { curiousNoteLink -> setNoteLink(curiousNoteLink.link)} }
    }
}