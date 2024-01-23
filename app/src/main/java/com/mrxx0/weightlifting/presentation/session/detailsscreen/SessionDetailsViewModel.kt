package com.mrxx0.weightlifting.presentation.session.detailsscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.Repository
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.mappers.toExercise
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.presentation.shared.SharedDataViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionDetailsViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase,
    private val sharedData: SharedDataViewModel
) : ViewModel() {

    private val repository = Repository(
        weightliftingDatabase.sessionDao(),
        weightliftingDatabase.exerciseDao(),
        weightliftingDatabase.setsDao()
    )

    private val _exerciseList = MutableLiveData<List<Exercise>>()
    val exerciseList: LiveData<List<Exercise>> get() = _exerciseList

    val sharedSession = sharedData.session

    fun getSessionById(sessionId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newSession = repository.getSessionById(sessionId).toSession()
                _exerciseList.postValue(
                    newSession.exercise
                )
                sharedData.updateSession(newSession)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun loadExerciseList(sessionId: Int) {
        viewModelScope.launch {
            _exerciseList.postValue(repository.getExercisesForSession(sessionId).map {
                it.toExercise()
            })
        }
    }

}