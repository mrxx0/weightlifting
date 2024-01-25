package com.mrxx0.weightlifting.presentation.set.create

sealed class SetCreateUiEvent {
    data class RepetitionsChanged(val repetitions: Int, val exerciseId: Int) : SetCreateUiEvent()
    data class WeightChanged(val weight: Int) : SetCreateUiEvent()
    data class RestTimeMinutesChanged(val minutes: Int) : SetCreateUiEvent()
    data class RestTimeSecondsChanged(val seconds: Int) : SetCreateUiEvent()
    data class RepeatChanged(val repeat: Int) : SetCreateUiEvent()
    data object SetCreateClicked : SetCreateUiEvent()
}