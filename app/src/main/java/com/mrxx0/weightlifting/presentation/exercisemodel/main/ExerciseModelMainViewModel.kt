package com.mrxx0.weightlifting.presentation.exercisemodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrxx0.weightlifting.domain.model.ExerciseModel
import com.mrxx0.weightlifting.domain.repository.ExerciseModelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseModelMainViewModel @Inject constructor(
    private val exerciseModelRepository: ExerciseModelRepository
) : ViewModel() {

    private val _allExercises = MutableLiveData<List<ExerciseModel>>()
    val allExercises: LiveData<List<ExerciseModel>> get() = _allExercises

    init {
        loadExercises()
    }

    fun loadExercises() {
        CoroutineScope(Dispatchers.IO).launch {
            _allExercises.postValue(
                exerciseModelRepository.getAllExercisesModel()
            )
        }
    }
}