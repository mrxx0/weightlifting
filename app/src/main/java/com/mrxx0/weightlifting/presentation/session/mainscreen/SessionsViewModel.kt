package com.mrxx0.weightlifting.presentation.session.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrxx0.weightlifting.data.local.Repository
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.mappers.toSession
import com.mrxx0.weightlifting.domain.model.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionsViewModel @Inject constructor(
    weightliftingDatabase: WeightliftingDatabase
) : ViewModel() {

    private val repository = Repository(
        weightliftingDatabase.sessionDao(),
        weightliftingDatabase.exerciseDao(),
        weightliftingDatabase.setsDao()
    )
    private val _allSessions = MutableLiveData<List<Session>>()
    val allSessions: LiveData<List<Session>> get() = _allSessions

    init {
        loadSession()
    }

    fun loadSession() {
        CoroutineScope(Dispatchers.IO).launch {
            _allSessions.postValue(
                repository.getAllSessions().map {
                    it.toSession()
                }
            )
        }
    }
}