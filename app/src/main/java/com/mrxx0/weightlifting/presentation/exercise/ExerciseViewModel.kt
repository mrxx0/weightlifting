package com.mrxx0.weightlifting.presentation.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.exercise.ExerciseRepository
import com.mrxx0.weightlifting.data.local.session.SessionRepository
import com.mrxx0.weightlifting.data.mappers.toExercises
import com.mrxx0.weightlifting.domain.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase
) : ViewModel() {

    private val sessionRepository = SessionRepository(
        weightliftingDatabase.sessionDao()
    )
    private val exerciseRepository = ExerciseRepository(
        weightliftingDatabase.exerciseDao()
    )

    val exerciseList: LiveData<List<ExerciseEntity>> get() = exerciseRepository.getExerciseListLiveData()

    val exercise: LiveData<Exercise> get() = exerciseRepository.getExerciseLiveData()

    fun createExercise(exercise: ExerciseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.insertExercise(exercise)
            val updateSession = sessionRepository.getSessionById(exercise.sessionId)
            updateSession.let {
                if (it.exercise.isNullOrEmpty()) {
                    it.exercise = mutableListOf(exercise)
                } else {
                    it.exercise!!.add(exercise)
                }
                sessionRepository.updateSession(updateSession)
            }
        }
    }

    fun loadExercises(sessionId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val currentExerciseList =
                exerciseRepository.getExercisesForSession(sessionId = sessionId)
            exerciseRepository.updateExerciseListLiveData(currentExerciseList)
        }
    }

    fun getExerciseById(exerciseId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newExercise = exerciseRepository.getExerciseById(exerciseId).toExercises()
                exerciseRepository.updateExerciseLiveData(newExercise)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}