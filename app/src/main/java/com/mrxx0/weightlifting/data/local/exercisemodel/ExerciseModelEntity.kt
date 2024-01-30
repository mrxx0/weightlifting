package com.mrxx0.weightlifting.data.local.exercisemodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercisemodel")
data class ExerciseModelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String? = null,
)
