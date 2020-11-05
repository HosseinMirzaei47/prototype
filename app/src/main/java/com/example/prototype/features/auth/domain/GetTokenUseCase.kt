package com.example.prototype.features.auth.domain

import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.core.utils.CoroutineUseCaseNoParameter
import com.example.prototype.features.auth.data.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    dispatchers: CoroutineDispatchers
) : CoroutineUseCaseNoParameter<Flow<String>>(dispatchers.io) {
    override suspend fun execute(): Flow<String> {
        return authRepository.getToken()
    }
}