package com.nguyen.jetnote.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguyen.jetnote.model.Note
import com.nguyen.jetnote.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect {
                if (it.isEmpty()) {
                    Log.d("TAGG", ": Empty List")
                } else {
                    _notes.value = it
                }
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }

    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }

    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
}