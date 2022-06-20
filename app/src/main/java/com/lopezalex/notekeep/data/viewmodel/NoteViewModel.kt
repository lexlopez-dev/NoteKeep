package com.lopezalex.notekeep.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lopezalex.notekeep.data.NoteDatabase
import com.lopezalex.notekeep.data.models.NoteData
import com.lopezalex.notekeep.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
    private val repository: NoteRepository

    val getAllData: LiveData<List<NoteData>>

    // Variable for sorting
    val sortByHighPriority: LiveData<List<NoteData>>
    val sortByLowPriority: LiveData<List<NoteData>>
    val sortByMediumPriority: LiveData<List<NoteData>>
    val sortByOldest: LiveData<List<NoteData>>

    init {
        repository = NoteRepository(noteDao)
        getAllData = repository.getAllData

        // Initializing sorting variables
        sortByHighPriority = repository.sortByHighPriority
        sortByLowPriority = repository.sortByLowPriority
        sortByMediumPriority = repository.sortByMediumPriority
        sortByOldest = repository.sortByOldest
    }

    // Function to insert data to database
    fun insertData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(noteData)
        }
    }

    // Function to update data in database
    fun updateData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(noteData)
        }
    }

    // Function to delete data in database
    fun deleteData(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(noteData)
        }
    }

    // Function to delete all data/notes
    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }

    // Function for searching the database
    fun searchDatabase(searchQuery: String): LiveData<List<NoteData>> {
        return repository.searchDatabase(searchQuery)
    }

}