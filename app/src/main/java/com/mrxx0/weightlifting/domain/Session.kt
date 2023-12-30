package com.mrxx0.weightlifting.domain

import java.io.Serializable

data class Session(
    val id: Int,
    var day: String,
    var exercise: MutableList<Exercises>? = null
) : Serializable
