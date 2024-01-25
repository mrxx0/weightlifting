package com.mrxx0.weightlifting.presentation.session.create

sealed class SessionCreateUiEvent {
    data class NameChanged(val name: String) : SessionCreateUiEvent()
    data object SessionCreateClicked : SessionCreateUiEvent()
}
