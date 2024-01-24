package com.mrxx0.weightlifting.domain.model

data class Session(
    val id: Int = 0,
    var day: String,
    var exercise: MutableList<Exercise>? = null
)
