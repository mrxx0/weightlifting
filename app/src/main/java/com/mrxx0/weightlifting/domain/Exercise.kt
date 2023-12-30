package com.mrxx0.weightlifting.domain

data class Exercises(
    val id: Int? = null,
    val name: String? = null,
    val sessionId: Int,
    val sets: List<Set>? = null,
)
