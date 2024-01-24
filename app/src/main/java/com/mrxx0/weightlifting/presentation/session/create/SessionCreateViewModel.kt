package com.mrxx0.weightlifting.presentation.session.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.domain.model.Session
import com.mrxx0.weightlifting.domain.usecase.CreateSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SessionCreateViewModel @Inject constructor(
    private val createSessionUseCase: CreateSessionUseCase
) : ViewModel() {

    fun createSession(day: String) {
        val newSession = Session(
            day = day
        )
        viewModelScope.launch {
            createSessionUseCase(newSession)
        }
    }
}