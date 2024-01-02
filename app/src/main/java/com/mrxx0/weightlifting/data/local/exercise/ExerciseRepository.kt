package com.mrxx0.weightlifting.data.local.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mrxx0.weightlifting.domain.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository(
    private val exerciseDao: ExerciseDao
) {
    private val _exercise = MutableLiveData<Exercise>()
    private val _exerciseList = MutableLiveData<List<ExerciseEntity>>()


    suspend fun insertExercise(exercise: ExerciseEntity) = withContext(Dispatchers.IO) {
        exerciseDao.insertExercises(exercise)
    }

    suspend fun updateExercise(exercise: ExerciseEntity) = withContext(Dispatchers.IO) {
        exerciseDao.updateExercises(exercise)
    }

    suspend fun deleteExercise(exercise: ExerciseEntity) = withContext(Dispatchers.IO) {
        exerciseDao.deleteExercises(exercise)
    }

    suspend fun getExercisesForSession(sessionId: Int): List<ExerciseEntity> =
        withContext(Dispatchers.IO) {
            exerciseDao.getExercisesForSession(sessionId)
        }

    suspend fun getExerciseById(exerciseId: Int): ExerciseEntity = withContext(Dispatchers.IO) {
        exerciseDao.getExerciseById(exerciseId)
    }

    fun updateExerciseLiveData(exercise: Exercise) {
        _exercise.postValue(exercise)
    }

    fun updateExerciseListLiveData(exerciseList: List<ExerciseEntity>) {
        _exerciseList.postValue(exerciseList)
    }

    fun getExerciseLiveData(): LiveData<Exercise> {
        return _exercise
    }

    fun getExerciseListLiveData(): LiveData<List<ExerciseEntity>> {
        return _exerciseList
    }
}