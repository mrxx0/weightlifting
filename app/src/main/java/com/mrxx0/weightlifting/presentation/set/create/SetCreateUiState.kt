package com.mrxx0.weightlifting.presentation.set.create

data class SetCreateUiState(
    val repetitions: Int = 0,
    val weight: Int = 0,
    val restTime: Int = 0,
    val restTimeMinutes: Int = 0,
    val restTimeSeconds: Int = 0,
    val exerciseId: Int = 0,
    val repeat: Int = 0,

    val repetitionsError: Boolean = false,
    val weightError: Boolean = false,
    val restTimeSecondsError: Boolean = false,
    val restTimeMinutesError: Boolean = false,
    val repeatError: Boolean = false,
    val setSaved: Boolean = false
)