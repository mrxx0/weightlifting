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
    val exercises: List<exercisesEntity>? = null
)

@Entity
data class exercisesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String? = null,
    val series: Int = 0,
    val repetitions: Int = 0,
    val restTime: Int = 0,
    val sessionId: Int = 0
)


class Converters {
    @TypeConverter
    fun fromString(value: String?): List<exercisesEntity>? {
        val listType = object : TypeToken<List<exercisesEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<exercisesEntity>?): String? {
        return Gson().toJson(list)
    }
}
