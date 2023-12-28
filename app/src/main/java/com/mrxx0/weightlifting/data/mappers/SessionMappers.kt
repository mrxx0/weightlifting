package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.ExercisesEntity
import com.mrxx0.weightlifting.data.local.SeriesEntity
import com.mrxx0.weightlifting.data.local.SessionEntity
import com.mrxx0.weightlifting.domain.Exercises
import com.mrxx0.weightlifting.domain.Series
import com.mrxx0.weightlifting.domain.Session

fun SessionEntity.toSession(): Session {
    return Session(
        id = id,
        day = day,
        exercises = this.exercises?.map { it.toExercises() }
    )
}

fun ExercisesEntity.toExercises(): Exercises {
    return Exercises(
        id = id,
        name = name,
        series = this.series?.map { it.toseries() }
    )
}

fun SeriesEntity.toseries(): Series {
    return Series(
        repetitions = repetitions,
        weight = weight,
        restTime = restTime
    )
}