package com.mrxx0.weightlifting.presentation.exercise.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.Repository
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.mappers.toExercise
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.presentation.shared.SharedDataViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseCreateViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase,
    private val sharedData: SharedDataViewModel
) : ViewModel() {

    private val repository = Repository(
        weightliftingDatabase.sessionDao(),
        weightliftingDatabase.exerciseDao(),
        weightliftingDatabase.setsDao()
    )

    fun createExercise(name: String, sessionId: Int) {
        val newExercise = ExerciseEntity(
            name = name,
            sessionId = sessionId
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertExercise(newExercise)
            sharedData.updateExercise(newExercise.toExercise())
            val updateSession = repository.getSessionById(sessionId)
            updateSession.let {
                if (it.exercise.isNullOrEmpty()) {
                    it.exercise = mutableListOf(newExercise)
                } else {
                    it.exercise!!.add(newExercise)
                }
                repository.updateSession(updateSession)
                sharedData.updateSession(updateSession.toSession())
            }
        }
    }
}