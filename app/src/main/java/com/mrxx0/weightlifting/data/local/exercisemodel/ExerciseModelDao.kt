package com.mrxx0.weightlifting.data.local.exercisemodel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExerciseModelDao {
    @Insert
    suspend fun insertExerciseModel(exerciseModel: ExerciseModelEntity)

    @Update
    suspend fun updateExerciseModel(exerciseModel: ExerciseModelEntity)

    @Delete
    suspend fun deleteExerciseModel(exerciseModel: ExerciseModelEntity)

    @Query("SELECT * FROM exercisemodel WHERE id = :exerciseModelId")
    suspend fun getExerciseModelById(exerciseModelId: Int): ExerciseModelEntity

    @Query("SELECT * FROM exercisemodel")
    suspend fun getAllExerciseModel(): List<ExerciseModelEntity>
}