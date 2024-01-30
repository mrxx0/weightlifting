package com.mrxx0.weightlifting.domain.repository

import com.mrxx0.weightlifting.domain.model.Exercise

interface ExerciseRepository {
    suspend fun insertExercise(exercise: Exercise)
    suspend fun updateExercise(exercise: Exercise)
    suspend fun deleteExercise(exercise: Exercise)
    suspend fun getExercisesForSession(sessionId: Int): List<Exercise>
    suspend fun getExerciseById(exerciseId: Int): Exercise
    suspend fun getAllExercises(): List<Exercise>
}