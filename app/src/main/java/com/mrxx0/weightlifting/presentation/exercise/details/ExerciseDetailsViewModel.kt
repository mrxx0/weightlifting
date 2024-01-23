package com.mrxx0.weightlifting.presentation.exercise.details

import androidx.lifecycle.ViewModel
import com.mrxx0.weightlifting.data.local.Repository
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.mappers.toExercise
import com.mrxx0.weightlifting.domain.model.Set
import com.mrxx0.weightlifting.presentation.shared.SharedDataViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase,
    private val sharedData: SharedDataViewModel
) : ViewModel() {

    private val repository = Repository(
        weightliftingDatabase.sessionDao(),
        weightliftingDatabase.exerciseDao(),
        weightliftingDatabase.setsDao()
    )
    val exercise = sharedData.exercise

    fun getExerciseById(exerciseId: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newExercise = repository.getExerciseById(exerciseId).toExercise()
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