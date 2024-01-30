package com.mrxx0.weightlifting.data.repository

import com.mrxx0.weightlifting.data.local.exercise.ExerciseDao
import com.mrxx0.weightlifting.data.mappers.toExercise
import com.mrxx0.weightlifting.data.mappers.toExerciseEntity
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.domain.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDao: ExerciseDao,
) : ExerciseRepository {
    override suspend fun insertExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        exerciseDao.insertExercises(exercise.toExerciseEntity())
    }

    override suspend fun updateExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        exerciseDao.updateExercises(exercise.toExerciseEntity())
    }

    override suspend fun deleteExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        exerciseDao.deleteExercises(exercise.toExerciseEntity())
    }

    override suspend fun getExercisesForSession(sessionId: Int): List<Exercise> =
        withContext(Dispatchers.IO) {
            exerciseDao.getExercisesForSession(sessionId).map {
                it.toExercise()
            }
        }

    override suspend fun getExerciseById(exerciseId: Int): Exercise =
        withContext(Dispatchers.IO) {
            exerciseDao.getExerciseById(exerciseId).toExercise()
        }

    override suspend fun getAllExercises(): List<Exercise> =
        withContext(Dispatchers.IO) {
            exerciseDao.getAllExercises().map {
                it.toExercise()
            }
        }
}