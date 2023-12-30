package com.mrxx0.weightlifting.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SessionRepository(
    private val sessionDao: SessionDao,
    private val exercisesDao: ExercisesDao,
    private val setsDao: SetsDao
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

    suspend fun insertExercises(exercises: ExercisesEntity) = withContext(Dispatchers.IO) {
        exercisesDao.insertExercises(exercises)
    }

    suspend fun updateExercises(exercises: ExercisesEntity) = withContext(Dispatchers.IO) {
        exercisesDao.updateExercises(exercises)
    }

    suspend fun deleteExercises(exercises: ExercisesEntity) = withContext(Dispatchers.IO) {
        exercisesDao.deleteExercises(exercises)
    }

    suspend fun getExercisesForSession(sessionId: Int): List<ExercisesEntity> =
        withContext(Dispatchers.IO) {
            exercisesDao.getExercisesForSession(sessionId)
        }

    suspend fun getExerciseById(exerciseId: Int): ExercisesEntity = withContext(Dispatchers.IO) {
        exercisesDao.getExerciseById(exerciseId)
    }

    suspend fun insertSets(sets: SetsEntity) = withContext(Dispatchers.IO) {
        setsDao.insertSets(sets)
    }

    suspend fun updateSets(sets: SetsEntity) = withContext(Dispatchers.IO) {
        setsDao.updateSets(sets)
    }

    suspend fun deleteSets(sets: SetsEntity) = withContext(Dispatchers.IO) {
        setsDao.deleteSets(sets)
    }

    suspend fun getSetsForExercises(exercisesId: Int): List<SetsEntity> =
        withContext(Dispatchers.IO) {
            setsDao.getSetsForExercises(exercisesId)
        }
}