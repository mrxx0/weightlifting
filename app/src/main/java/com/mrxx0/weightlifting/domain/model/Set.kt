package com.mrxx0.weightlifting.domain.model

data class Set(
    val id: Int = 0,
    val repetitions: Int,
    val weight: Int,
    val restTime: Int,
    val exerciseId: Int,
    val repeat: Int
)
