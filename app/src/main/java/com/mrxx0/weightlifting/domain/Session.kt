package com.mrxx0.weightlifting.domain

import java.io.Serializable

data class Session(
    val id: Int,
    var day: String,
    var exercises: MutableList<Exercises>? = null
) : Serializable

data class Exercises(
    val id: Int? = null,
    val name: String? = null,
    val sessionId: Int,
    val series: List<Series>? = null
)

data class Series(
    val id: Int,
    val repetitions: Int,
    val weight: Int,
    val restTime: Int,
    val exercisesId: Int
)
