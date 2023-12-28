package com.mrxx0.weightlifting.data.local

import com.mrxx0.weightlifting.data.mappers.toExercises
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.domain.Exercises
import com.mrxx0.weightlifting.domain.Session
import kotlinx.coroutines.flow.Flow

class SessionRepository(
    private val sessionDao: SessionDao
) {

    val allSessions: Flow<List<SessionEntity>> = sessionDao.getAllSessions()

    fun getExercisesForSession(sessionId: Int): Flow<List<ExercisesEntity>> {
        return sessionDao.getExercisesForSession(sessionId)
    }

    suspend fun getSessionById(sessionId: Int): Session? {
        return sessionDao.getSessionById(sessionId)?.toSession()
    }

    suspend fun getExerciseById(exerciseId: Int): Exercises? {
        return sessionDao.getExerciseById(exerciseId)?.toExercises()
    }

    suspend fun getAllSessionIds(): List<Int> {
        return sessionDao.getAllSessionIds()
    }

    suspend fun insertSession(session: SessionEntity) {
        sessionDao.insertSession(session)
    }

    suspend fun deleteSession(sessionId: Int) {
        sessionDao.deleteSession(sessionId)
    }

    suspend fun deleteAllSession() {
        sessionDao.deleteAllSession()
    }
}