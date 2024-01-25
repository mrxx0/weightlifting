package com.mrxx0.weightlifting.presentation.session.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrxx0.weightlifting.data.rules.Validator
import com.mrxx0.weightlifting.domain.model.Session
import com.mrxx0.weightlifting.domain.usecase.CreateSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SessionCreateViewModel @Inject constructor(
    private val createSessionUseCase: CreateSessionUseCase
) : ViewModel() {

    val sessionCreateUIState = mutableStateOf(SessionCreateUiState())

    fun onEvent(event: SessionCreateUiEvent) {
        when (event) {
            is SessionCreateUiEvent.NameChanged -> {
                sessionCreateUIState.value = sessionCreateUIState.value.copy(
                    name = event.name
                )
            }

            is SessionCreateUiEvent.SessionCreateClicked -> {
                validateSessionCreateUIDataWithRules()
                if (!sessionCreateUIState.value.nameError) {
                    createSession()
                }
            }
        }
        validateSessionCreateUIDataWithRules()
    }

    private fun validateSessionCreateUIDataWithRules() {
        val nameResult = Validator.validateNameField(
            name = sessionCreateUIState.value.name
        )
        sessionCreateUIState.value = sessionCreateUIState.value.copy(
            nameError = nameResult.status
        )

    }

    private fun createSession() {
        val newSession = Session(
            name = sessionCreateUIState.value.name
        )
        viewModelScope.launch {
            createSessionUseCase(newSession)
        }
        sessionCreateUIState.value = sessionCreateUIState.value.copy(
            sessionSaved = true
        )
    }
}