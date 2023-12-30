package com.mrxx0.weightlifting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.Repository
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.local.set.SetEntity
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

    fun createSession(session: SessionEntity) {
        viewModelScope.launch {
            repository.insertSession(session)
        }
    }

    fun createExercise(exercise: ExerciseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertExercise(exercise)
            val updateSession = repository.getSessionById(exercise.sessionId)
            updateSession.let {
                if (it.exercise.isNullOrEmpty()) {
                    it.exercise = mutableListOf(exercise)
                } else {
                    it.exercise!!.add(exercise)
                }
                repository.updateSession(updateSession)
                _session.postValue(updateSession.toSession())
            }
        }
    }

    fun createSet(set: SetEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSet(set)
            val updateExercise = repository.getExerciseById(set.exerciseId)
            updateExercise.let {
                if (it.sets.isNullOrEmpty()) {
                    it.sets = mutableListOf(set)
                } else {
                    it.sets!!.add(set)
                }
                repository.updateExercise(updateExercise)
                _exercise.postValue(updateExercise.toExercises())
            }
        }
    }

    fun getSetValueFromExercise(sets: List<Set>): Int {
        var total = 0
        for (setDetails in sets) {
            if (setDetails.repeat > 0) {
                total += setDetails.repeat
            } else {
                total += 1
            }
        }
        return total
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