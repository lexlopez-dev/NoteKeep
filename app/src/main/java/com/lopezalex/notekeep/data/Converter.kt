package com.lopezalex.notekeep.data

import androidx.room.TypeConverter
import com.lopezalex.notekeep.data.models.Priority

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}