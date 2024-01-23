package com.mrxx0.weightlifting.domain.model

import java.io.Serializable

data class Session(
    val id: Int,
    var day: String,
    var exercise: MutableList<Exercise>? = null
) : Serializable
