package com.mrxx0.weightlifting.presentation.set

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class SetViewModel : ViewModel() {

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

}