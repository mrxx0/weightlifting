package com.mrxx0.weightlifting.presentation.session.create

data class SessionCreateUiState(
    val name: String = "",
    val nameError: Boolean = false,
    val sessionSaved: Boolean = false
)
