package com.lopezalex.notekeep.data.repository

import androidx.lifecycle.LiveData
import com.lopezalex.notekeep.data.NoteDao
import com.lopezalex.notekeep.data.models.NoteData

class NoteRepository(private val noteDao: NoteDao) {

    // Getting all data (also used for default sorting newest)
    val getAllData: LiveData<List<NoteData>> = noteDao.getAllData()

    // Variables for sorting
    val sortByHighPriority: LiveData<List<NoteData>> = noteDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<NoteData>> = noteDao.sortByLowPriority()
    val sortByMediumPriority: LiveData<List<NoteData>> = noteDao.sortByMediumPriority()
    val sortByOldest: LiveData<List<NoteData>> = noteDao.sortByOldest()

    // Inserting data
    suspend fun insertData(noteData: NoteData) {
        noteDao.insertData(noteData)
    }

    // Updating data
    suspend fun updateData(noteData: NoteData) {
        noteDao.updateData(noteData)
    }

    // Deleting data
    suspend fun deleteData(noteData: NoteData) {
        noteDao.deleteItem(noteData)
    }

    // Deleting all data/notes
    suspend fun deleteAllData() {
        noteDao.deleteAll()
    }

    // Function for searching the database
    fun searchDatabase(searchQuery: String): LiveData<List<NoteData>> {
        return noteDao.searchDatabase(searchQuery)
    }



}