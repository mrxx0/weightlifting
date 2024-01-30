package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.exercisemodel.ExerciseModelEntity
import com.mrxx0.weightlifting.domain.model.ExerciseModel

fun ExerciseModelEntity.toExerciseModel(): ExerciseModel {
    return ExerciseModel(
        id = this.id,
        name = this.name
    )
}

fun ExerciseModel.toExerciseModelEntity(): ExerciseModelEntity {
    return ExerciseModelEntity(
        id = this.id,
        name = this.name
    )
}