package com.mrxx0.weightlifting.data.local

import com.mrxx0.weightlifting.data.local.exercise.ExerciseDao
import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.session.SessionDao
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.local.set.SetDao
import com.mrxx0.weightlifting.data.local.set.SetEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val sessionDao: SessionDao,
    private val exerciseDao: ExerciseDao,
    private val setsDao: SetDao
) {

    suspend fun insertSession(session: SessionEntity) = withContext(Dispatchers.IO) {
        sessionDao.insertSession(session)
    }

    suspend fun updateSession(session: SessionEntity) = withContext(Dispatchers.IO) {
        sessionDao.updateSession(session)
    }

    suspend fun deleteSession(session: SessionEntity) = withContext(Dispatchers.IO) {
        sessionDao.deleteSession(session)
    }

    suspend fun getAllSessions(): List<SessionEntity> = withContext(Dispatchers.IO) {
        sessionDao.getAllSessions()
    }

    suspend fun getSessionById(sessionId: Int): SessionEntity = withContext(Dispatchers.IO) {
        sessionDao.getSessionById(sessionId)
    }

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

    suspend fun insertSet(sets: SetEntity) = withContext(Dispatchers.IO) {
        setsDao.insertSets(sets)
    }

    suspend fun updateSet(sets: SetEntity) = withContext(Dispatchers.IO) {
        setsDao.updateSets(sets)
    }

    suspend fun deleteSet(sets: SetEntity) = withContext(Dispatchers.IO) {
        setsDao.deleteSets(sets)
    }

    suspend fun getSetsForExercise(exerciseId: Int): List<SetEntity> =
        withContext(Dispatchers.IO) {
            setsDao.getSetsForExercises(exerciseId)
        }
}