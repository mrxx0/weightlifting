package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.exercisemodel.ExerciseModelEntity
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.local.set.SetEntity
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.domain.model.ExerciseModel
import com.mrxx0.weightlifting.domain.model.Session
import com.mrxx0.weightlifting.domain.model.Set

fun SessionEntity.toSession(): Session {
    return Session(
        id = this.id,
        name = this.name,
        exercise = this.exercise?.map { it.toExercise() } as MutableList<Exercise>?
    )
}

fun Session.toSessionEntity(): SessionEntity {
    return SessionEntity(
        id = this.id,
        name = this.name,
        exercise = this.exercise?.map { it.toExerciseEntity() }?.toMutableList()
    )
}





