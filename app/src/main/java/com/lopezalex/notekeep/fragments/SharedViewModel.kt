package com.lopezalex.notekeep.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.lopezalex.notekeep.R
import com.lopezalex.notekeep.data.models.NoteData
import com.lopezalex.notekeep.data.models.Priority

class SharedViewModel(application: Application): AndroidViewModel(application) {

    /** ------------------------ List Fragment ------------------------------------- */

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    /*
    * This function checks if there is any data every time the data is observed.
    * 'emptyDatabase' changes to true or false.
    * */
    fun checkIfDatabaseIsEmpty(noteData: List<NoteData>) {
        emptyDatabase.value = noteData.isEmpty()
    }

    /** ------------------------ Add/Update Fragment ------------------------------------- */

    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when (position) {
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red)) }
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow)) }
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green)) }
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    // Function returns false if title or description is empty, returns true otherwise
    fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }

    // Parse priority string to priority object
    fun parsePriority(priority: String): Priority {
        return when(priority) {
            "High Priority" -> { Priority.HIGH }
            "Medium Priority" -> { Priority.MEDIUM }
            "Low Priority" -> { Priority.LOW }
            else -> Priority.LOW
        }
    }

}