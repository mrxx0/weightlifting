package com.mrxx0.weightlifting.domain

import java.io.Serializable

data class Session(
    val day: String,
    val exercises: List<exercises>? = null
) : Serializable

data class exercises(
    val name: String? = null,
    val series: Int = 0,
    val repetitions: Int = 0,
    val restTime: Int = 0
)
