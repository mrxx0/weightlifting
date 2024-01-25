package com.mrxx0.weightlifting.presentation.exercise.create

sealed class ExerciseCreateUiEvent {
    data class NameChanged(val name: String, val sessionId: Int) : ExerciseCreateUiEvent()
    data object ExerciseCreateClicked : ExerciseCreateUiEvent()
}
