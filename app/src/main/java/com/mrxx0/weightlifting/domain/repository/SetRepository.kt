package com.mrxx0.weightlifting.domain.repository

import com.mrxx0.weightlifting.domain.model.Set


interface SetRepository {
    suspend fun insertSet(set: Set)
    suspend fun updateSet(set: Set)
    suspend fun deleteSet(set: Set)
    suspend fun getSetsForExercise(exerciseId: Int): List<Set>
}