package com.mrxx0.weightlifting.domain.usecase

import com.mrxx0.weightlifting.domain.model.ExerciseModel
import com.mrxx0.weightlifting.domain.repository.ExerciseModelRepository
import javax.inject.Inject

class CreateExerciseModelUseCase @Inject constructor(
    private val exerciseModelRepository: ExerciseModelRepository,
) {
    suspend operator fun invoke(exerciseModel: ExerciseModel) {
        exerciseModelRepository.insertExerciseModel(exerciseModel)
    }
}