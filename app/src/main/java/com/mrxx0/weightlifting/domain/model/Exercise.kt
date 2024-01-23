package com.mrxx0.weightlifting.domain.model

data class Exercise(
    val id: Int? = null,
    val name: String? = null,
    val sessionId: Int,
    val sets: List<Set>? = null,
)
