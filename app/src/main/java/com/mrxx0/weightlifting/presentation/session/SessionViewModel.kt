package com.mrxx0.weightlifting.presentation.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.local.exercise.ExerciseRepository
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.local.session.SessionRepository
import com.mrxx0.weightlifting.data.local.set.SetRepository
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.domain.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase
) : ViewModel() {

    private val sessionRepository = SessionRepository(
        weightliftingDatabase.sessionDao()
    )

    private val exerciseRepository = ExerciseRepository(
        weightliftingDatabase.exerciseDao()
    )

    private val setRepository = SetRepository(
        weightliftingDatabase.setDao()
    )

    private val _allSessions = MutableLiveData<List<Session>>()
    val allSessions: LiveData<List<Session>> get() = _allSessions

    val session: LiveData<Session> get() = sessionRepository.getSessionLiveData()

    private val _sessionDeleteMode = MutableLiveData(false)
    val sessionDeleteMode: LiveData<Boolean> get() = _sessionDeleteMode

    private val _sessionDeleteId = MutableLiveData<List<Int>>()
    val sessionDeleteId: LiveData<List<Int>> get() = _sessionDeleteId

    init {
        loadSession()
    }

    fun createSession(session: SessionEntity) {
        viewModelScope.launch {
            sessionRepository.insertSession(session)
        }
    }

    fun getSessionById(sessionId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newSession = sessionRepository.getSessionById(sessionId).toSession()
                sessionRepository.updateSessionLiveData(newSession)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun loadSession() {
        CoroutineScope(Dispatchers.IO).launch {
            _allSessions.postValue(
                sessionRepository.getAllSessions().map {
                    it.toSession()
                }
            )
        }
    }

    fun startSessionDeleteMode(sessionId: Int) {
        viewModelScope.launch {
            _sessionDeleteMode.postValue(true)
            val listSessionToDelete = _sessionDeleteId.value?.toMutableList() ?: mutableListOf()
            listSessionToDelete.add(sessionId)
            _sessionDeleteId.postValue(listSessionToDelete)
        }
    }

    fun stopSessionDeleteMode() {
        viewModelScope.launch {
            _sessionDeleteMode.postValue(false)
            _sessionDeleteId.value = mutableListOf()
            _sessionDeleteId.postValue(_sessionDeleteId.value)
        }
    }

    fun addSessionToDelete(sessionId: Int) {
        viewModelScope.launch {
            val currentList = _sessionDeleteId.value?.toMutableList() ?: mutableListOf()
            currentList.add(sessionId)
            _sessionDeleteId.postValue(currentList)
        }
    }

    fun removeSessionToDelete(sessionId: Int) {
        viewModelScope.launch {
            val currentList = _sessionDeleteId.value?.toMutableList() ?: mutableListOf()
            currentList.removeAt(currentList.indexOf(sessionId))
            if (currentList.isEmpty()) {
                stopSessionDeleteMode()
            }
            _sessionDeleteId.postValue(currentList)
        }
    }

    fun deleteSession() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_sessionDeleteMode.value == true) {
                for (sessionId in _sessionDeleteId.value!!) {
                    val sessionToDelete = sessionRepository.getSessionById(sessionId)
                    if (sessionToDelete.exercise != null && sessionToDelete.exercise!!.size > 0) {
                        val exerciseListToDelete =
                            exerciseRepository.getExercisesForSession(sessionId)
                        for (exerciseToDelete in exerciseListToDelete) {
                            if (exerciseToDelete.sets != null && exerciseToDelete.sets!!.size > 0) {
                                val setListToDelete =
                                    setRepository.getSetsForExercise(exerciseToDelete.id)
                                for (setToDelete in setListToDelete) {
                                    setRepository.deleteSet(setToDelete)
                                }
                            }
                            exerciseRepository.deleteExercise(exerciseToDelete)
                        }
                    }
                    sessionRepository.deleteSession(sessionToDelete)
                }
                _allSessions.postValue(
                    sessionRepository.getAllSessions().map {
                        it.toSession()
                    }
                )
                _sessionDeleteId.postValue(emptyList())
                _sessionDeleteMode.postValue(false)
            }
        }
    }

    fun updateSession(session: SessionEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            sessionRepository.updateSession(session)
        }
    }
}