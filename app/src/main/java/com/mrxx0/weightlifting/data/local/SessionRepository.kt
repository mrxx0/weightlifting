package com.mrxx0.weightlifting.data.local

import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.domain.Session

class SessionRepository(
    private val sessionDao: SessionDao
) {

    suspend fun getAllSessions(): List<Session>? {
        return sessionDao.getAllSessions()?.map { it.toSession() }
    }

    suspend fun insertSession(session: SessionEntity) {
        sessionDao.insertSession(session)
    }

    suspend fun deleteSession(session: SessionEntity) {
        sessionDao.deleteSession(session)
    }

    suspend fun deleteAllSession() {
        sessionDao.deleteAllSession()
    }
}