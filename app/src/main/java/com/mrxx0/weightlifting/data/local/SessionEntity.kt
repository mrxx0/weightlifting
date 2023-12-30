package com.mrxx0.weightlifting.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    var exercises: MutableList<ExercisesEntity>? = null
)

@Entity(
    tableName = "exercises",
    foreignKeys = [ForeignKey(
        entity = SessionEntity::class,
        parentColumns = ["id"],
        childColumns = ["sessionId"],
        onDelete = CASCADE
    )]
)
data class ExercisesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String? = null,
    val series: List<SeriesEntity>? = null,
    val sessionId: Int // Foreign key linking to SessionEntity
)

@Entity(
    tableName = "series",
    foreignKeys = [ForeignKey(
        entity = ExercisesEntity::class,
        parentColumns = ["id"],
        childColumns = ["exercisesId"],
        onDelete = CASCADE
    )]
)
data class SeriesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val repetitions: Int,
    val weight: Int,
    val restTime: Int,
    val exercisesId: Int // Foreign key linking to ExercisesEntity
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
