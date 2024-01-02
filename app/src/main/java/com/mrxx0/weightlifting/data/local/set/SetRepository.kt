package com.mrxx0.weightlifting.data.local.set

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetRepository(
    private val setDao: SetDao
) {
    suspend fun insertSet(sets: SetEntity) = withContext(Dispatchers.IO) {
        setDao.insertSets(sets)
    }

    suspend fun updateSet(sets: SetEntity) = withContext(Dispatchers.IO) {
        setDao.updateSets(sets)
    }

    suspend fun deleteSet(sets: SetEntity) = withContext(Dispatchers.IO) {
        setDao.deleteSets(sets)
    }

    suspend fun getSetsForExercise(exerciseId: Int): List<SetEntity> =
        withContext(Dispatchers.IO) {
            setDao.getSetsForExercises(exerciseId)
        }
}