package com.mrxx0.weightlifting.presentation.exercisemodel.details

import androidx.lifecycle.ViewModel
import com.mrxx0.weightlifting.domain.model.Set
import com.mrxx0.weightlifting.domain.repository.ExerciseRepository
import com.mrxx0.weightlifting.presentation.shared.SharedDataViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val sharedData: SharedDataViewModel
) : ViewModel() {

    val exercise = sharedData.exercise

    fun getExerciseById(exerciseId: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newExercise = exerciseRepository.getExerciseById(exerciseId)
                sharedData.updateExercise(newExercise)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun getSetValueFromExercise(sets: List<Set>): Int {
        var total = 0
        for (setDetails in sets) {
            total += if (setDetails.repeat > 0) {
                setDetails.repeat
            } else {
                1
            }
        }
        return total
    }
}