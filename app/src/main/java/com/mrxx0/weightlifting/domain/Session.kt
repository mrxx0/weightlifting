package com.mrxx0.weightlifting.domain

data class Session(
    val day: String,
    val exercises: List<exercises>? = null
)

data class exercises(
    val name: String? = null,
    val series: Int = 0,
    val repetitions: Int = 0,
    val restTime: Int = 0
)
