package com.mrxx0.weightlifting.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.set.SetEntity

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<ExerciseEntity>? {
        val listType = object : TypeToken<List<ExerciseEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ExerciseEntity>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromSetsString(value: String?): List<SetEntity>? {
        val listType = object : TypeToken<List<SetEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromSetsList(list: List<SetEntity>?): String? {
        return Gson().toJson(list)
    }
}