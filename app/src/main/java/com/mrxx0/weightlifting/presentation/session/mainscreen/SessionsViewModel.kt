package com.mrxx0.weightlifting.presentation.session.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrxx0.weightlifting.domain.model.Session
import com.mrxx0.weightlifting.domain.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionsViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _allSessions = MutableLiveData<List<Session>>()
    val allSessions: LiveData<List<Session>> get() = _allSessions

    init {
        loadSession()
    }

    fun loadSession() {
        CoroutineScope(Dispatchers.IO).launch {
            _allSessions.postValue(
                sessionRepository.getAllSessions()
            )
        }
    }
}