package com.mrxx0.weightlifting.presentation.exercisemodel.create

sealed class ExerciseModelCreateUiEvent {
    data class NameChanged(val name: String) : ExerciseModelCreateUiEvent()
    data object ExerciseCreateClicked : ExerciseModelCreateUiEvent()
}
