package com.mrxx0.weightlifting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.SessionDatabase
import com.mrxx0.weightlifting.data.local.SessionEntity
import com.mrxx0.weightlifting.data.local.SessionRepository
import com.mrxx0.weightlifting.data.local.exercisesEntity
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.domain.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    sessionDatabase: SessionDatabase
) : ViewModel() {

    private val sessionRepository = SessionRepository(sessionDatabase.dao)

    private val _sessionList = MutableLiveData<List<Session>?>()
    val sessionList: LiveData<List<Session>?> get() = _sessionList

    init {
        loadSession()
    }

    fun createSession(day: String) {
        viewModelScope.launch {
            val exercise = exercisesEntity(
                id = 0,
                name = "Squat",
                restTime = 200,
                repetitions = 8,
                series = 5
            )
            val exercisesList = mutableListOf<exercisesEntity>()
            repeat(5) {
                exercisesList.add(exercise)
            }
            val sessionEntity = SessionEntity(day = day, exercises = exercisesList)
            sessionRepository.insertSession(sessionEntity)
            val currentList = _sessionList.value ?: emptyList()
            val updatedList = currentList + sessionEntity.toSession()
            _sessionList.postValue(updatedList)
        }
    }

    fun deleteSession(session: SessionEntity) {
        viewModelScope.launch {
            sessionRepository.deleteSession(session)
        }
    }

    fun loadSession() {
        viewModelScope.launch {
            _sessionList.value = sessionRepository.getAllSessions()
        }
    }

    fun deleteAllSession() {
        viewModelScope.launch {
            sessionRepository.deleteAllSession()
            val currentList = sessionRepository.getAllSessions()
            _sessionList.postValue(currentList)
        }
    }
}