package com.mrxx0.weightlifting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.ExercisesEntity
import com.mrxx0.weightlifting.data.local.SeriesEntity
import com.mrxx0.weightlifting.data.local.SessionDatabase
import com.mrxx0.weightlifting.data.local.SessionEntity
import com.mrxx0.weightlifting.data.local.SessionRepository
import com.mrxx0.weightlifting.domain.Exercises
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

    private val sessionRepository = SessionRepository(sessionDatabase.dao)
    private val allSessions = sessionRepository.allSessions

    private val _session = MutableLiveData<Session>()
    val session: LiveData<Session> get() = _session

    private val _exercise = MutableLiveData<Exercises>()
    val exercise: LiveData<Exercises> get() = _exercise

    private val _sessionList = MutableLiveData<List<SessionEntity>>()
    val sessionList: LiveData<List<SessionEntity>> get() = _sessionList


    init {
        loadSession()
    }

    fun createSession(day: String) {
        viewModelScope.launch {
            val series = SeriesEntity(
                repetitions = 8,
                weight = 140,
                restTime = 120
            )
            val seriesList = mutableListOf<SeriesEntity>()
            repeat(4) {
                seriesList.add(series)
            }
            val exercise = ExercisesEntity(
                id = 0,
                name = "Squat",
                series = seriesList
            )

            val exercisesList = mutableListOf<ExercisesEntity>()
            repeat(5) {
                exercisesList.add(exercise)
            }
            val sessionEntity = SessionEntity(day = day, exercises = exercisesList)
            sessionRepository.insertSession(sessionEntity)
        }
    }

    fun getSessionById(sessionId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newSession = sessionRepository.getSessionById(sessionId)
                _session.postValue(newSession)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun getExerciseById(exerciseId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newExercise = sessionRepository.getExerciseById(exerciseId)
                _exercise.postValue(newExercise)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


    fun loadSession() {
        CoroutineScope(Dispatchers.IO).launch {
            allSessions.collect {
                _sessionList.postValue(it)
            }
        }
    }
}