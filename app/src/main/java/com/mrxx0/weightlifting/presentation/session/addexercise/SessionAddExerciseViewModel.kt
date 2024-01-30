package com.mrxx0.weightlifting.presentation.session.addexercise

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.domain.model.ExerciseModel
import com.mrxx0.weightlifting.presentation.shared.SharedDataViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionAddExerciseViewModel @Inject constructor(
) : ViewModel() {

    private val _selectedExerciseModel = mutableStateListOf<ExerciseModel>()
    val selectedExerciseModel : List<ExerciseModel> get() = _selectedExerciseModel

    fun onCheckedExerciseModel(exerciseModel: ExerciseModel) {
        if (!_selectedExerciseModel.contains(exerciseModel)) {
            _selectedExerciseModel.add(exerciseModel)
        }
    }

}