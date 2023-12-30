package com.mrxx0.weightlifting.domain

data class Set(
    val id: Int,
    val repetitions: Int,
    val weight: Int,
    val restTime: Int,
    val exerciseId: Int,
    val repeat: Int
)
