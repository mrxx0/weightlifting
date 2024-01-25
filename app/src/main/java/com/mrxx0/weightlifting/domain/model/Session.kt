package com.mrxx0.weightlifting.domain.model

data class Session(
    val id: Int = 0,
    var name: String,
    var exercise: MutableList<Exercise>? = null
)
