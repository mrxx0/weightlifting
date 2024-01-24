package com.mrxx0.weightlifting.domain.usecase

import com.mrxx0.weightlifting.data.mappers.toExercise
import com.mrxx0.weightlifting.data.mappers.toExerciseEntity
import com.mrxx0.weightlifting.data.mappers.toSetEntity
import com.mrxx0.weightlifting.domain.model.Set
import com.mrxx0.weightlifting.domain.repository.ExerciseRepository
import com.mrxx0.weightlifting.domain.repository.SetRepository
import com.mrxx0.weightlifting.presentation.shared.SharedDataViewModel
import javax.inject.Inject

class CreateSetUseCase @Inject constructor(
    private val setRepository: SetRepository,
    private val exerciseRepository: ExerciseRepository,
    private val sharedDataViewModel: SharedDataViewModel
) {
    suspend operator fun invoke(set: Set) {
        setRepository.insertSet(set)
        val updateExercise = exerciseRepository.getExerciseById(set.exerciseId).toExerciseEntity()
        updateExercise.let {
            if (it.sets.isNullOrEmpty()) {
                it.sets = mutableListOf(set.toSetEntity())
            } else {
                it.sets!!.add(set.toSetEntity())
            }
            exerciseRepository.updateExercise(updateExercise.toExercise())
            sharedDataViewModel.updateExercise(updateExercise.toExercise())
        }
    }
}