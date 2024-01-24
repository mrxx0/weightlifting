package com.mrxx0.weightlifting.data.repository

import com.mrxx0.weightlifting.data.local.session.SessionDao
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.data.mappers.toSessionEntity
import com.mrxx0.weightlifting.domain.model.Session
import com.mrxx0.weightlifting.domain.repository.SessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SessionRepositoryImpl(
    private val sessionDao: SessionDao
) : SessionRepository {
    override suspend fun insertSession(session: Session) = withContext(Dispatchers.IO) {
        sessionDao.insertSession(session.toSessionEntity())
    }

    override suspend fun updateSession(session: Session) = withContext(Dispatchers.IO) {
        sessionDao.updateSession(session.toSessionEntity())
    }

    override suspend fun deleteSession(session: Session) = withContext(Dispatchers.IO) {
        sessionDao.deleteSession(session.toSessionEntity())
    }

    override suspend fun getAllSessions(): List<Session> = withContext(Dispatchers.IO) {
        sessionDao.getAllSessions().map {
            it.toSession()
        }
    }

    override suspend fun getSessionById(sessionId: Int): Session =
        withContext(Dispatchers.IO) {
            sessionDao.getSessionById(sessionId).toSession()
        }
}