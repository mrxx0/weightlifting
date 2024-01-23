package com.mrxx0.weightlifting.presentation.session.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.local.Repository
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SessionCreateViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase,
) : ViewModel() {

    private val repository = Repository(
        weightliftingDatabase.sessionDao(),
        weightliftingDatabase.exerciseDao(),
        weightliftingDatabase.setsDao()
    )

    fun createSession(day: String) {
        val newSession = SessionEntity(
            day = day
        )
        viewModelScope.launch {
            repository.insertSession(newSession)
        }
    }
}