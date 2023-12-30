package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.local.set.SetEntity
import com.mrxx0.weightlifting.domain.Exercises
import com.mrxx0.weightlifting.domain.Session
import com.mrxx0.weightlifting.domain.Set

fun SessionEntity.toSession(): Session {
    return Session(
        id = id,
        day = day,
        exercise = this.exercise?.map { it.toExercises() } as MutableList<Exercises>?
    )
}

fun ExerciseEntity.toExercises(): Exercises {
    return Exercises(
        id = this.id,
        name = this.name,
        sessionId = this.sessionId,
        sets = this.sets?.map { it.toSets() }
    )
}

fun SetEntity.toSets(): Set {
    return Set(
        id = id,
        repetitions = repetitions,
        weight = weight,
        restTime = restTime,
        exerciseId = this.exerciseId
    )
}

fun Session.toSessionEntity(): SessionEntity {
    return SessionEntity(
        id = id,
        day = day,
        exercise = this.exercise?.map { it.toExerciseEntity() }?.toMutableList()
    )
}

fun Exercises.toExerciseEntity(): ExerciseEntity {
    return ExerciseEntity(
        id = this.id ?: 0,
        name = name,
        sessionId = this.sessionId,
        sets = this.sets?.map { it.toSetEntity() }
    )
}

fun Set.toSetEntity(): SetEntity {
    return SetEntity(
        id = id,
        repetitions = repetitions,
        weight = weight,
        restTime = restTime,
        exerciseId = this.exerciseId
    )
}