package com.mrxx0.weightlifting.data.local.exercise

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insertExercises(exercise: ExerciseEntity)

    @Update
    suspend fun updateExercises(exercise: ExerciseEntity)

    @Delete
    suspend fun deleteExercises(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercise WHERE sessionId = :sessionId")
    suspend fun getExercisesForSession(sessionId: Int): List<ExerciseEntity>

    @Query("SELECT * FROM exercise WHERE id = :exerciseId")
    suspend fun getExerciseById(exerciseId: Int): ExerciseEntity
}