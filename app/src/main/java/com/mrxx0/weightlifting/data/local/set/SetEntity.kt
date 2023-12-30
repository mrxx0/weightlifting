package com.mrxx0.weightlifting.data.local.set

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity


@Entity(
    tableName = "sets",
    foreignKeys = [ForeignKey(
        entity = ExerciseEntity::class,
        parentColumns = ["id"],
        childColumns = ["exerciseId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val repetitions: Int,
    val weight: Int,
    val restTime: Int,
    val exerciseId: Int
)
