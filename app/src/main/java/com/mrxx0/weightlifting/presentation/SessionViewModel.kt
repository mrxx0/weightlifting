package com.mrxx0.weightlifting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.Repository
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.mappers.toExercises
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.domain.Exercises
import com.mrxx0.weightlifting.domain.Session
import com.mrxx0.weightlifting.domain.Set
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase
) : ViewModel() {

    private val repository = Repository(
        weightliftingDatabase.sessionDao(),
        weightliftingDatabase.exerciseDao(),
        weightliftingDatabase.setsDao()
    )

    private val _allSessions = MutableLiveData<List<Session>>()
    val allSessions: LiveData<List<Session>> get() = _allSessions

    private val _session = MutableLiveData<Session>()
    val session: LiveData<Session> get() = _session

    private val _exercise = MutableLiveData<Exercises>()
    val exercise: LiveData<Exercises> get() = _exercise
    private val _sets = MutableLiveData<Set>()
    val sets: LiveData<Set> get() = _sets

    private val _exerciseList = MutableLiveData<List<ExerciseEntity>>()
    val exerciseList: LiveData<List<ExerciseEntity>> get() = _exerciseList


    init {
        loadSession()
    }

    fun createSession(day: String) {
        viewModelScope.launch {
            val sessionEntity = SessionEntity(day = day)
            repository.insertSession(sessionEntity)
        }
    }

    fun createExercise(exercise: ExerciseEntity) {
        viewModelScope.launch {
            repository.insertExercise(exercise)
            val updateSession = repository.getSessionById(exercise.sessionId)
            if (updateSession.exercise.isNullOrEmpty()) {
                updateSession.exercise = mutableListOf(exercise)
            } else {
                updateSession.exercise!!.add(exercise)
            }
            repository.updateSession(updateSession)
            CoroutineScope(Dispatchers.IO).launch {
                _session.postValue(updateSession.toSession())
            }
        }
    }

    fun getSessionById(sessionId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newSession = repository.getSessionById(sessionId).toSession()
                _session.postValue(newSession)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun getExerciseById(exerciseId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newExercise = repository.getExerciseById(exerciseId).toExercises()
                _exercise.postValue(newExercise)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


    fun loadSession() {
        CoroutineScope(Dispatchers.IO).launch {
            _allSessions.postValue(
                repository.getAllSessions().map {
                    it.toSession()
                }
            )
        }
    }

    fun loadExercises(sessionId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _exerciseList.postValue(
                repository.getExercisesForSession(sessionId = sessionId)
            )
        }
    }
}