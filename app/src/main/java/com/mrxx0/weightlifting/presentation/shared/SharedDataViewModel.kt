package com.mrxx0.weightlifting.presentation.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.domain.model.Session
import kotlinx.coroutines.launch
import javax.inject.Inject

class SharedDataViewModel @Inject constructor() : ViewModel() {
    private val _session = MutableLiveData<Session>()
    val session: LiveData<Session> get() = _session
    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise> get() = _exercise

    fun updateSession(newSession: Session) {
        viewModelScope.launch {
            _session.postValue(newSession)
        }
    }

    fun updateExercise(newExercise: Exercise) {
        viewModelScope.launch {
            _exercise.postValue(newExercise)
        }
    }
}