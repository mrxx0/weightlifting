package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.domain.model.Exercise

fun ExerciseEntity.toExercise(): Exercise {
    return Exercise(
        id = this.id,
        name = this.name,
        sessionId = this.sessionId,
        set = this.set?.map { it.toSet() }
    )
}

fun Exercise.toExerciseEntity(): ExerciseEntity {
    return ExerciseEntity(
        id = this.id,
        name = this.name,
        sessionId = this.sessionId,
        set = this.set?.map { it.toSetEntity() }?.toMutableList()
    )
}

