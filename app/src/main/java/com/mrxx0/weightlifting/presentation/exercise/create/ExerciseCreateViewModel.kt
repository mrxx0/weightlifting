package com.mrxx0.weightlifting.presentation.exercise.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.rules.Validator
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

    val exerciseCreateUiState = mutableStateOf(ExerciseCreateUiState())

    fun onEvent(event: ExerciseCreateUiEvent) {
        when (event) {
            is ExerciseCreateUiEvent.NameChanged -> {
                exerciseCreateUiState.value = exerciseCreateUiState.value.copy(
                    name = event.name,
                    sessionId = event.sessionId
                )
            }

            is ExerciseCreateUiEvent.ExerciseCreateClicked -> {
                validateExerciseCreateUIDataWithRules()
                if (!exerciseCreateUiState.value.nameError) {
                    createExercise()
                }
            }
        }
        validateExerciseCreateUIDataWithRules()
    }

    private fun validateExerciseCreateUIDataWithRules() {
        val nameResult = Validator.validateNameField(
            name = exerciseCreateUiState.value.name
        )
        exerciseCreateUiState.value = exerciseCreateUiState.value.copy(
            nameError = nameResult.status
        )

    }

    private fun createExercise() {
        val newExercise = Exercise(
            name = exerciseCreateUiState.value.name,
            sessionId = exerciseCreateUiState.value.sessionId
        )
        viewModelScope.launch(Dispatchers.IO) {
            createExerciseUseCase(newExercise)
        }
        exerciseCreateUiState.value = exerciseCreateUiState.value.copy(
            exerciseSaved = true
        )
    }
}