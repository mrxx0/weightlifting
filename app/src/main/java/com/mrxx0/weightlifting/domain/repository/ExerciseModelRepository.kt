package com.mrxx0.weightlifting.domain.repository

import com.mrxx0.weightlifting.domain.model.ExerciseModel

interface ExerciseModelRepository {
    suspend fun insertExerciseModel(exerciseModel: ExerciseModel)
    suspend fun updateExerciseModel(exerciseModel: ExerciseModel)
    suspend fun deleteExerciseModel(exerciseModel: ExerciseModel)
    suspend fun getExerciseModelById(exerciseId: Int): ExerciseModel
    suspend fun getAllExercisesModel(): List<ExerciseModel>
}