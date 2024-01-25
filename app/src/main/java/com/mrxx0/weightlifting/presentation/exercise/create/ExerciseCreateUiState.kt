package com.mrxx0.weightlifting.presentation.exercise.create

data class ExerciseCreateUiState(
    val name: String = "",
    val sessionId: Int = 0,
    val nameError: Boolean = false,
    val exerciseSaved: Boolean = false
)
