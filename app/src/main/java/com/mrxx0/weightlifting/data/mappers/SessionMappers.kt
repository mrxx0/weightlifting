package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.local.set.SetEntity
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.domain.model.Session
import com.mrxx0.weightlifting.domain.model.Set

fun SessionEntity.toSession(): Session {
    return Session(
        id = this.id,
        name = this.name,
        exercise = this.exercise?.map { it.toExercise() } as MutableList<Exercise>?
    )
}

fun ExerciseEntity.toExercise(): Exercise {
    return Exercise(
        id = this.id,
        name = this.name,
        sessionId = this.sessionId,
        set = this.set?.map { it.toSet() }
    )
}

fun SetEntity.toSet(): Set {
    return Set(
        id = this.id,
        repetitions = this.repetitions,
        weight = this.weight,
        restTime = this.restTime,
        exerciseId = this.exerciseId,
        repeat = this.repeat
    )
}

fun Session.toSessionEntity(): SessionEntity {
    return SessionEntity(
        id = this.id,
        name = this.name,
        exercise = this.exercise?.map { it.toExerciseEntity() }?.toMutableList()
    )
}

fun Exercise.toExerciseEntity(): ExerciseEntity {
    return ExerciseEntity(
        id = this.id ?: 66,
        name = this.name,
        sessionId = this.sessionId,
        set = this.set?.map { it.toSetEntity() }?.toMutableList()
    )
}

fun Set.toSetEntity(): SetEntity {
    return SetEntity(
        id = this.id,
        repetitions = this.repetitions,
        weight = this.weight,
        restTime = this.restTime,
        exerciseId = this.exerciseId,
        repeat = this.repeat
    )
}