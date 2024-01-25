package com.mrxx0.weightlifting.domain.model

data class Exercise(
    val id: Int = 0,
    val name: String? = null,
    val sessionId: Int,
    val set: List<Set>? = null,
)
