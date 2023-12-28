package com.mrxx0.weightlifting.domain

import java.io.Serializable

data class Session(
    val id: Int,
    val day: String,
    val exercises: List<Exercises>? = null
) : Serializable

data class Exercises(
    val id: Int,
    val name: String? = null,
    val series: List<Series>? = null
)

data class Series(
    val repetitions: Int,
    val weight: Int,
    val restTime: Int
)
