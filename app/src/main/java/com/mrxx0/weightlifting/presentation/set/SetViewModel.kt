package com.mrxx0.weightlifting.presentation.set

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.local.exercise.ExerciseRepository
import com.mrxx0.weightlifting.data.local.set.SetEntity
import com.mrxx0.weightlifting.data.local.set.SetRepository
import com.mrxx0.weightlifting.data.mappers.toExercises
import com.mrxx0.weightlifting.domain.Set
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase
) : ViewModel() {

    private val setRepository = SetRepository(
        weightliftingDatabase.setDao()
    )

    private val exerciseRepository = ExerciseRepository(
        weightliftingDatabase.exerciseDao()
    )

    val restTime: MutableState<Int> = mutableIntStateOf(0)
    val restTimeMinutes: MutableState<Int> = mutableIntStateOf(0)
    val restTimeSeconds: MutableState<Int> = mutableIntStateOf(0)
    val repetitions: MutableState<Int> = mutableIntStateOf(0)
    val weight: MutableState<Int> = mutableIntStateOf(0)
    val repeat: MutableState<Int> = mutableIntStateOf(0)

    fun updateRestTime(newRestTime: Int) {
        restTime.value = newRestTime
    }

    fun updateRestTimeMinutes(newRestTimeMinutes: Int) {
        restTimeMinutes.value = newRestTimeMinutes
    }

    fun convertTimeToSeconds(): Int {
        return ((restTimeMinutes.value * 60) + restTimeSeconds.value)
    }

    fun updateRestTimeSeconds(newRestTimeSeconds: Int) {
        restTimeSeconds.value = newRestTimeSeconds
    }

    fun updateRepetitions(newRepetitions: Int) {
        repetitions.value = newRepetitions
    }

    fun updateWeight(newWeight: Int) {
        weight.value = newWeight
    }

    fun updateRepeat(newRepeat: Int) {
        repeat.value = newRepeat
    }

    fun createSet(set: SetEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            setRepository.insertSet(set)
            val updateExercise = exerciseRepository.getExerciseById(set.exerciseId)
            updateExercise.let {
                if (it.sets.isNullOrEmpty()) {
                    it.sets = mutableListOf(set)
                } else {
                    it.sets!!.add(set)
                }
                exerciseRepository.updateExercise(updateExercise)
                exerciseRepository.updateExerciseLiveData(updateExercise.toExercises())
            }
        }
    }

    fun getSetValueFromExercise(sets: List<Set>): Int {
        var total = 0
        for (setDetails in sets) {
            if (setDetails.repeat > 0) {
                total += setDetails.repeat
            } else {
                total += 1
            }
        }
        return total
    }

}