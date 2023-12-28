package com.mrxx0.weightlifting.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class SessionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(defaultValue = "0")
    val id: Int = 0,
    val day: String,
    val exercises: List<ExercisesEntity>? = null
)

@Entity
data class ExercisesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String? = null,
    val series: List<SeriesEntity>? = null,
    val sessionId: Int = 0
)

data class SeriesEntity(
    val repetitions: Int,
    val weight: Int,
    val restTime: Int
)


class Converters {
    @TypeConverter
    fun fromString(value: String?): List<ExercisesEntity>? {
        val listType = object : TypeToken<List<ExercisesEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ExercisesEntity>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromSeriesString(value: String?): List<SeriesEntity>? {
        val listType = object : TypeToken<List<SeriesEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromSeriesList(list: List<SeriesEntity>?): String? {
        return Gson().toJson(list)
    }
}
