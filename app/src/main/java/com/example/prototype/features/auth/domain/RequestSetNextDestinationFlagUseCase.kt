package com.example.prototype.features.auth.domain

import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.core.utils.CoroutineUseCase
import com.example.prototype.features.auth.data.AuthRepository
import javax.inject.Inject

class RequestSetNextDestinationFlagUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    dispatchers: CoroutineDispatchers
) : CoroutineUseCase<Boolean, Unit>(dispatchers.io) {
    override suspend fun execute(parameters: Boolean) {
        authRepository.setNextDestinationFlag(parameters)
    }
}