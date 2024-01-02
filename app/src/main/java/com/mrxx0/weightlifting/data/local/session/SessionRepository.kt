package com.mrxx0.weightlifting.data.local.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.domain.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SessionRepository(
    private val sessionDao: SessionDao
) {

    private val _session = MutableLiveData<Session>()
    val session: LiveData<Session> get() = _session

    suspend fun insertSession(session: SessionEntity) = withContext(Dispatchers.IO) {
        sessionDao.insertSession(session)
    }

    suspend fun updateSession(session: SessionEntity) = withContext(Dispatchers.IO) {
        sessionDao.updateSession(session)
        _session.postValue(session.toSession())

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

    fun getSessionLiveData(): LiveData<Session> {
        return session
    }

    fun updateSessionLiveData(session: Session) {
        _session.postValue(session)
    }
}