package com.lopezalex.notekeep.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lopezalex.notekeep.data.models.NoteData

@Dao
interface NoteDao {

    // Also the default sorting (shows newest notes first)
    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllData(): LiveData<List<NoteData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(noteData: NoteData)

    @Update
    suspend fun updateData(noteData: NoteData)

    @Delete
    suspend fun deleteItem(noteData: NoteData)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    // Searching the DB
    @Query("SELECT * FROM note_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<NoteData>>

    // Sorting the db by high priority
    @Query("SELECT * FROM note_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<NoteData>>

    // Sorting the db by low priority
    @Query("SELECT * FROM note_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<NoteData>>

    // Sorting the db by med priority
    @Query("SELECT * FROM note_table ORDER BY CASE WHEN priority LIKE 'M%' THEN 1 WHEN priority LIKE 'L%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByMediumPriority(): LiveData<List<NoteData>>

    // Sort by oldest notes
    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun sortByOldest(): LiveData<List<NoteData>>

}