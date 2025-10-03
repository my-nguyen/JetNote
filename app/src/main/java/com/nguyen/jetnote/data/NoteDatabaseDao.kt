package com.nguyen.jetnote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nguyen.jetnote.model.Note

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * from notes_tbl")
    fun selectAll(): List<Note>

    @Query("SELECT * from notes_tbl where id =:id")
    suspend fun selectById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: Note)

}