package com.example.prototype.features.home.domain

import com.example.prototype.core.CoroutineUseCase
import com.example.prototype.features.home.data.AuthRepository
import com.example.prototype.features.home.data.AuthRequest
import com.example.prototype.features.home.data.RegisterResponse
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RequestRegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : CoroutineUseCase<AuthRequest, RegisterResponse>(Dispatchers.IO) {
    override suspend fun execute(parameters: AuthRequest): RegisterResponse {
        val registerUser = authRepository.registerUser(parameters)
        registerUser?.let {
            return registerUser
        }
        return RegisterResponse(-1, "")
    }
}