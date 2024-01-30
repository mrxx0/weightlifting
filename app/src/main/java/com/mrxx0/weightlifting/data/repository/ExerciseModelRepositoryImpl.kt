package com.mrxx0.weightlifting.data.repository

import com.mrxx0.weightlifting.data.local.exercisemodel.ExerciseModelDao
import com.mrxx0.weightlifting.data.mappers.toExerciseModel
import com.mrxx0.weightlifting.data.mappers.toExerciseModelEntity
import com.mrxx0.weightlifting.domain.model.ExerciseModel
import com.mrxx0.weightlifting.domain.repository.ExerciseModelRepository
import javax.inject.Inject

class ExerciseModelRepositoryImpl @Inject constructor(
    private val exerciseModelDao: ExerciseModelDao,
) : ExerciseModelRepository {
    override suspend fun insertExerciseModel(exerciseModel: ExerciseModel) {
        exerciseModelDao.insertExerciseModel(exerciseModel.toExerciseModelEntity())
    }

    override suspend fun updateExerciseModel(exerciseModel: ExerciseModel) {
        exerciseModelDao.updateExerciseModel(exerciseModel.toExerciseModelEntity())
    }

    override suspend fun deleteExerciseModel(exerciseModel: ExerciseModel) {
        exerciseModelDao.deleteExerciseModel(exerciseModel.toExerciseModelEntity())
    }

    override suspend fun getExerciseModelById(exerciseId: Int): ExerciseModel {
        return exerciseModelDao.getExerciseModelById(exerciseId).toExerciseModel()
    }

    override suspend fun getAllExercisesModel(): List<ExerciseModel> {
        return exerciseModelDao.getAllExerciseModel().map {
            it.toExerciseModel()
        }
    }

}