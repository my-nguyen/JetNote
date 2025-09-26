package com.nguyen.jetnote.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.nguyen.jetnote.data.NoteDataSource
import com.nguyen.jetnote.model.Note

@RequiresApi(Build.VERSION_CODES.O)
class NoteViewModel : ViewModel() {
    val notes = mutableStateListOf<Note>()

    init {
        notes.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun removeNote(note: Note) {
        notes.remove(note)
    }

    fun getNotes(): List<Note> = notes
}