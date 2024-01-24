package com.mrxx0.weightlifting.presentation.exercise.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.domain.usecase.CreateExerciseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseCreateViewModel @Inject constructor(
    private val createExerciseUseCase: CreateExerciseUseCase
) : ViewModel() {

    fun createExercise(name: String, sessionId: Int) {
        val newExercise = Exercise(
            name = name,
            sessionId = sessionId
        )
        viewModelScope.launch(Dispatchers.IO) {
            createExerciseUseCase(newExercise)
        }
    }
}