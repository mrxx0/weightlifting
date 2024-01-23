package com.mrxx0.weightlifting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.Repository
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.local.set.SetEntity
import com.mrxx0.weightlifting.data.mappers.toExercise
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.domain.model.Session
import com.mrxx0.weightlifting.domain.model.Set
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase
) : ViewModel() {

    private val repository = Repository(
        weightliftingDatabase.sessionDao(),
        weightliftingDatabase.exerciseDao(),
        weightliftingDatabase.setsDao()
    )

    private val _session = MutableLiveData<Session>()
    val session: LiveData<Session> get() = _session

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise> get() = _exercise
    private val _sets = MutableLiveData<Set>()
    val sets: LiveData<Set> get() = _sets


    fun createSet(set: SetEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSet(set)
            val updateExercise = repository.getExerciseById(set.exerciseId)
            updateExercise.let {
                if (it.sets.isNullOrEmpty()) {
                    it.sets = mutableListOf(set)
                } else {
                    it.sets!!.add(set)
                }
                repository.updateExercise(updateExercise)
                _exercise.postValue(updateExercise.toExercise())
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

    fun getExerciseById(exerciseId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newExercise = repository.getExerciseById(exerciseId).toExercise()
                _exercise.postValue(newExercise)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}