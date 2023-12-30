package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.local.set.SetEntity
import com.mrxx0.weightlifting.domain.Exercises
import com.mrxx0.weightlifting.domain.Session
import com.mrxx0.weightlifting.domain.Set

fun SessionEntity.toSession(): Session {
    return Session(
        id = this.id,
        day = this.day,
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
        id = this.id,
        repetitions = this.repetitions,
        weight = this.weight,
        restTime = this.restTime,
        exerciseId = this.exerciseId,
        clone = this.clone
    )
}

fun Session.toSessionEntity(): SessionEntity {
    return SessionEntity(
        id = this.id,
        day = this.day,
        exercise = this.exercise?.map { it.toExerciseEntity() }?.toMutableList()
    )
}

fun Exercises.toExerciseEntity(): ExerciseEntity {
    return ExerciseEntity(
        id = this.id ?: 0,
        name = this.name,
        sessionId = this.sessionId,
        sets = this.sets?.map { it.toSetEntity() }?.toMutableList()
    )
}

fun Set.toSetEntity(): SetEntity {
    return SetEntity(
        id = this.id,
        repetitions = this.repetitions,
        weight = this.weight,
        restTime = this.restTime,
        exerciseId = this.exerciseId,
        clone = this.clone
    )
}