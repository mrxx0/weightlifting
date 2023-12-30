package com.mrxx0.weightlifting.data.local.session

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    var exercise: MutableList<ExerciseEntity>? = null
)

