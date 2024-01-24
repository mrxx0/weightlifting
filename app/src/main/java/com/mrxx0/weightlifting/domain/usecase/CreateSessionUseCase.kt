package com.mrxx0.weightlifting.domain.usecase

import com.mrxx0.weightlifting.domain.model.Session
import com.mrxx0.weightlifting.domain.repository.SessionRepository
import javax.inject.Inject


class CreateSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(session: Session) {
        sessionRepository.insertSession(session)
    }
}