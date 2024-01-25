package com.mrxx0.weightlifting.presentation.set.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.rules.Validator
import com.mrxx0.weightlifting.domain.model.Set
import com.mrxx0.weightlifting.domain.usecase.CreateSetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetCreateViewModel @Inject constructor(
    private val createSetUseCase: CreateSetUseCase,
) : ViewModel() {

    val setCreateUiState = mutableStateOf(SetCreateUiState())
    val allValidationsPassed = mutableStateOf(false)

    fun onEvent(event: SetCreateUiEvent) {
        when (event) {
            is SetCreateUiEvent.RepetitionsChanged -> {
                setCreateUiState.value = setCreateUiState.value.copy(
                    repetitions = event.repetitions,
                    exerciseId = event.exerciseId
                )
            }

            is SetCreateUiEvent.RepeatChanged -> {
                setCreateUiState.value = setCreateUiState.value.copy(
                    repeat = event.repeat
                )
            }

            is SetCreateUiEvent.RestTimeMinutesChanged -> {
                setCreateUiState.value = setCreateUiState.value.copy(
                    restTime = event.minutes
                )
            }

            is SetCreateUiEvent.RestTimeSecondsChanged -> {
                setCreateUiState.value = setCreateUiState.value.copy(
                    restTime = event.seconds
                )
            }

            is SetCreateUiEvent.WeightChanged -> {
                setCreateUiState.value = setCreateUiState.value.copy(
                    weight = event.weight
                )
            }

            is SetCreateUiEvent.SetCreateClicked -> {
                validateSetCreateDataWithRules()
                if (allValidationsPassed.value) {
                    createSet()
                }
            }

        }
        validateSetCreateDataWithRules()
    }

    private fun validateSetCreateDataWithRules() {
        val repetitionsResult = Validator.validateIntFieldSubEqualZero(
            value = setCreateUiState.value.repetitions
        )
        val weightResult = Validator.validateIntFieldSubEqualZero(
            value = setCreateUiState.value.weight
        )
        val secondResult = Validator.validateIntFieldSubEqualZero(
            value = setCreateUiState.value.restTimeSeconds
        )
        val minuteResult = Validator.validateIntFieldSubEqualZero(
            value = setCreateUiState.value.restTimeMinutes
        )
        val repeatResult = Validator.validateIntFieldSubZero(
            value = setCreateUiState.value.repeat
        )
        allValidationsPassed.value =
            repetitionsResult.status &&
                    weightResult.status &&
                    repeatResult.status
    }

    private fun createSet() {
        val newSet = Set(
            repetitions = setCreateUiState.value.repetitions,
            repeat = setCreateUiState.value.repeat,
            weight = setCreateUiState.value.weight,
            restTime = setCreateUiState.value.restTime,
            exerciseId = setCreateUiState.value.exerciseId
        )
        viewModelScope.launch(Dispatchers.IO) {
            createSetUseCase(newSet)
        }
        setCreateUiState.value = setCreateUiState.value.copy(
            setSaved = true
        )
    }

}