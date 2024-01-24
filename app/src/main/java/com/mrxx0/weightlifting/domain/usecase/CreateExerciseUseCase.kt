package com.mrxx0.weightlifting.domain.usecase

import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.domain.repository.ExerciseRepository
import com.mrxx0.weightlifting.domain.repository.SessionRepository
import com.mrxx0.weightlifting.presentation.shared.SharedDataViewModel
import javax.inject.Inject

class CreateExerciseUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val sessionRepository: SessionRepository,
    private val sharedDataViewModel: SharedDataViewModel
) {
    suspend operator fun invoke(exercise: Exercise) {
        exerciseRepository.insertExercise(exercise)
        sharedDataViewModel.updateExercise(exercise)
        val updateSession = sessionRepository.getSessionById(exercise.sessionId)
        updateSession.let {
            if (it.exercise.isNullOrEmpty()) {
                it.exercise = mutableListOf(exercise)
            } else {
                it.exercise!!.add(exercise)
            }
            sessionRepository.updateSession(updateSession)
            sharedDataViewModel.updateSession(updateSession)
        }
    }
}