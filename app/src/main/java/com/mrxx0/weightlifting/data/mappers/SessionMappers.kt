package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.ExercisesEntity
import com.mrxx0.weightlifting.data.local.SetsEntity
import com.mrxx0.weightlifting.data.local.SessionEntity
import com.mrxx0.weightlifting.domain.Exercises
import com.mrxx0.weightlifting.domain.Sets
import com.mrxx0.weightlifting.domain.Session

fun SessionEntity.toSession(): Session {
    return Session(
        id = id,
        day = day,
        exercises = this.exercises?.map { it.toExercises() } as MutableList<Exercises>?
    )
}

fun ExercisesEntity.toExercises(): Exercises {
    return Exercises(
        id = this.id,
        name = this.name,
        sessionId = this.sessionId,
        sets = this.sets?.map { it.toSets() }
    )
}

fun SetsEntity.toSets(): Sets {
    return Sets(
        id = id,
        repetitions = repetitions,
        weight = weight,
        restTime = restTime,
        exercisesId = this.exercisesId
    )
}

fun Session.toSessionEntity(): SessionEntity {
    return SessionEntity(
        id = id,
        day = day,
        exercises = this.exercises?.map { it.toExercisesEntity() }?.toMutableList()
    )
}

fun Exercises.toExercisesEntity(): ExercisesEntity {
    return ExercisesEntity(
        id = this.id ?: 0,
        name = name,
        sessionId = this.sessionId,
        sets = this.sets?.map { it.toSetsEntity() }
    )
}

fun Sets.toSetsEntity(): SetsEntity {
    return SetsEntity(
        id = id,
        repetitions = repetitions,
        weight = weight,
        restTime = restTime,
        exercisesId = this.exercisesId
    )
}