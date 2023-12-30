package com.mrxx0.weightlifting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.ExercisesEntity
import com.mrxx0.weightlifting.data.local.SessionDatabase
import com.mrxx0.weightlifting.data.local.SessionEntity
import com.mrxx0.weightlifting.data.local.SessionRepository
import com.mrxx0.weightlifting.data.mappers.toExercises
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.domain.Exercises
import com.mrxx0.weightlifting.domain.Series
import com.mrxx0.weightlifting.domain.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    sessionDatabase: SessionDatabase
) : ViewModel() {

    private val sessionRepository = SessionRepository(
        sessionDatabase.sessionDao(),
        sessionDatabase.exercisesDao(),
        sessionDatabase.seriesDao()
    )

    private val _allSessions = MutableLiveData<List<Session>>()
    val allSessions: LiveData<List<Session>> get() = _allSessions

    private val _session = MutableLiveData<Session>()
    val session: LiveData<Session> get() = _session

    private val _exercise = MutableLiveData<Exercises>()
    val exercise: LiveData<Exercises> get() = _exercise
    private val _series = MutableLiveData<Series>()
    val series: LiveData<Series> get() = _series

    private val _exerciseList = MutableLiveData<List<ExercisesEntity>>()
    val exerciseList: LiveData<List<ExercisesEntity>> get() = _exerciseList


    init {
        loadSession()
    }

    fun createSession(day: String) {
        viewModelScope.launch {
            val sessionEntity = SessionEntity(day = day)
            sessionRepository.insertSession(sessionEntity)
        }
    }

    fun createExercise(exercise: ExercisesEntity) {
        viewModelScope.launch {
            sessionRepository.insertExercises(exercise)
            val updateSession = sessionRepository.getSessionById(exercise.sessionId)
            if (updateSession.exercises.isNullOrEmpty()) {
                updateSession.exercises = mutableListOf(exercise)
            } else {
                updateSession.exercises!!.add(exercise)
            }
            sessionRepository.updateSession(updateSession)
            CoroutineScope(Dispatchers.IO).launch {
                _session.postValue(updateSession.toSession())
            }
        }
    }

    fun getSessionById(sessionId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newSession = sessionRepository.getSessionById(sessionId).toSession()
                _session.postValue(newSession)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun getExerciseById(exerciseId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newExercise = sessionRepository.getExerciseById(exerciseId).toExercises()
                _exercise.postValue(newExercise)
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

    fun loadExercises(sessionId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _exerciseList.postValue(
                sessionRepository.getExercisesForSession(sessionId = sessionId)
            )
        }
    }
}