package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.SessionEntity
import com.mrxx0.weightlifting.data.local.exercisesEntity
import com.mrxx0.weightlifting.domain.Session
import com.mrxx0.weightlifting.domain.exercises

fun SessionEntity.toSession(): Session {
    return Session(
        day = day,
        exercises = this.exercises?.map { it.toexercises() }
    )
}

fun exercisesEntity.toexercises(): exercises {
    return exercises(
        name = name,
        series = series,
        repetitions = repetitions,
        restTime = restTime
    )
}