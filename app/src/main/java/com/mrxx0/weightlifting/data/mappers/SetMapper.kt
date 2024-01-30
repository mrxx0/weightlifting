package com.mrxx0.weightlifting.data.mappers

import com.mrxx0.weightlifting.data.local.set.SetEntity
import com.mrxx0.weightlifting.domain.model.Set

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