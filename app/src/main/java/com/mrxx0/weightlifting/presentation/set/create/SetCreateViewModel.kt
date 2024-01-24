package com.mrxx0.weightlifting.presentation.set.create

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.domain.model.Set
import com.mrxx0.weightlifting.domain.usecase.CreateSetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetCreateViewModel @Inject constructor(
    private val createSetUseCase: CreateSetUseCase,
) : ViewModel() {

    val restTimeMinutes: MutableState<Int> = mutableIntStateOf(0)
    val restTimeSeconds: MutableState<Int> = mutableIntStateOf(0)
    val repetitions: MutableState<Int> = mutableIntStateOf(0)
    val weight: MutableState<Int> = mutableIntStateOf(0)
    val repeat: MutableState<Int> = mutableIntStateOf(0)

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

    fun createSet(repetitions: Int, repeat: Int, weight: Int, restTime: Int, exerciseId: Int) {
        val newSet = Set(
            repetitions = repetitions,
            repeat = repeat,
            weight = weight,
            restTime = restTime,
            exerciseId = exerciseId
        )
        viewModelScope.launch(Dispatchers.IO) {
            createSetUseCase(newSet)
        }
    }

}