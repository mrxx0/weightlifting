package com.mrxx0.weightlifting.data.repository

import com.mrxx0.weightlifting.data.local.set.SetDao
import com.mrxx0.weightlifting.data.mappers.toSet
import com.mrxx0.weightlifting.data.mappers.toSetEntity
import com.mrxx0.weightlifting.domain.model.Set
import com.mrxx0.weightlifting.domain.repository.SetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetRepositoryImpl(
    private val setDao: SetDao
) : SetRepository {

    override suspend fun insertSet(set: Set) = withContext(Dispatchers.IO) {
        setDao.insertSets(set.toSetEntity())
    }

    override suspend fun updateSet(set: Set) = withContext(Dispatchers.IO) {
        setDao.updateSets(set.toSetEntity())
    }

    override suspend fun deleteSet(set: Set) = withContext(Dispatchers.IO) {
        setDao.deleteSets(set.toSetEntity())
    }

    override suspend fun getSetsForExercise(exerciseId: Int): List<Set> =
        withContext(Dispatchers.IO) {
            setDao.getSetsForExercises(exerciseId).map {
                it.toSet()
            }
        }
}