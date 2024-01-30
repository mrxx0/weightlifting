package com.mrxx0.weightlifting.presentation.session.detailsscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.domain.repository.ExerciseRepository
import com.mrxx0.weightlifting.domain.repository.SessionRepository
import com.mrxx0.weightlifting.presentation.shared.SharedDataViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionDetailsViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val exerciseRepository: ExerciseRepository,
    private val sharedData: SharedDataViewModel
) : ViewModel() {

    private val _exerciseList = MutableLiveData<List<Exercise>?>(emptyList())
    val exerciseList: LiveData<List<Exercise>?> get() = _exerciseList
    val sharedSession = sharedData.session

    fun getSessionById(sessionId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newSession = sessionRepository.getSessionById(sessionId)
                _exerciseList.postValue(
                    newSession.exercise
                )
                sharedData.updateSession(newSession)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


//    fun loadExerciseList(sessionId: Int) {
//        viewModelScope.launch {
//            _exerciseList.postValue(exerciseRepository.getExercisesForSession(sessionId))
//        }
//    }

}