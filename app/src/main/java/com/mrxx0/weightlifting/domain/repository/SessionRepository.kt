package com.mrxx0.weightlifting.domain.repository

import com.mrxx0.weightlifting.domain.model.Session


interface SessionRepository {
    suspend fun insertSession(session: Session)
    suspend fun updateSession(session: Session)
    suspend fun deleteSession(session: Session)
    suspend fun getAllSessions(): List<Session>
    suspend fun getSessionById(sessionId: Int): Session
}