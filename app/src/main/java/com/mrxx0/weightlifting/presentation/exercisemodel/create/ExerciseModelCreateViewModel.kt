package com.mrxx0.weightlifting.presentation.exercisemodel.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.rules.Validator
import com.mrxx0.weightlifting.domain.model.ExerciseModel
import com.mrxx0.weightlifting.domain.usecase.CreateExerciseModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseModelCreateViewModel @Inject constructor(
    private val createExerciseModelUseCase: CreateExerciseModelUseCase
) : ViewModel() {

    val exerciseModelCreateUiState = mutableStateOf(ExerciseModelCreateUiState())

    fun onEvent(event: ExerciseModelCreateUiEvent) {
        when (event) {
            is ExerciseModelCreateUiEvent.NameChanged -> {
                exerciseModelCreateUiState.value = exerciseModelCreateUiState.value.copy(
                    name = event.name,
                )
            }

            is ExerciseModelCreateUiEvent.ExerciseCreateClicked -> {
                validateExerciseCreateUIDataWithRules()
                if (!exerciseModelCreateUiState.value.nameError) {
                    createExercise()
                }
            }
        }
        validateExerciseCreateUIDataWithRules()
    }

    private fun validateExerciseCreateUIDataWithRules() {
        val nameResult = Validator.validateNameField(
            name = exerciseModelCreateUiState.value.name
        )
        exerciseModelCreateUiState.value = exerciseModelCreateUiState.value.copy(
            nameError = nameResult.status
        )

    }

    private fun createExercise() {
        val newExerciseModel = ExerciseModel(
            name = exerciseModelCreateUiState.value.name
        )
        viewModelScope.launch(Dispatchers.IO) {
            createExerciseModelUseCase(newExerciseModel)
        }
        exerciseModelCreateUiState.value = exerciseModelCreateUiState.value.copy(
            exerciseSaved = true
        )
    }
}