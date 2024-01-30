package com.mrxx0.weightlifting.presentation.exercisemodel.create

data class ExerciseModelCreateUiState(
    val name: String = "",
    val nameError: Boolean = false,
    val exerciseSaved: Boolean = false
)
