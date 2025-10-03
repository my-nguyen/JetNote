package com.nguyen.jetnote.repository

import com.nguyen.jetnote.data.NoteDatabaseDao
import com.nguyen.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val dao: NoteDatabaseDao) {
    fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes().flowOn(Dispatchers.IO).conflate()

    suspend fun addNote(note: Note) = dao.insertNote(note)
    suspend fun updateNote(note: Note) = dao.updateNote(note)
    suspend fun deleteNote(note: Note) = dao.deleteNote(note)
    suspend fun deleteAllNotes() = dao.deleteAllNotes()
    suspend fun getNoteById(id: String) = dao.getNoteById(id)
}