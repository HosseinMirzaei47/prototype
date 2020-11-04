package com.example.prototype.features.auth.domain

import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.core.utils.CoroutineUseCase
import com.example.prototype.features.auth.data.AuthRepository
import com.example.prototype.features.auth.data.AuthRequest
import com.example.prototype.features.auth.data.RegisterResponse
import javax.inject.Inject

class RequestRegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    dispatchers: CoroutineDispatchers
) : CoroutineUseCase<AuthRequest, RegisterResponse>(dispatchers.io) {
    override suspend fun execute(parameters: AuthRequest): RegisterResponse {
        val registerUser = authRepository.registerUser(parameters)
        registerUser?.let {
            return registerUser
        }
        return RegisterResponse(-1, "")
    }
}