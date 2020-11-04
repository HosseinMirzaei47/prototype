package com.example.prototype.features.auth.domain

import com.example.prototype.core.utils.CoroutineUseCase
import com.example.prototype.features.auth.data.AuthRepository
import com.example.prototype.features.auth.data.AuthRequest
import com.example.prototype.features.auth.data.LoginResponse
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RequestLoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : CoroutineUseCase<AuthRequest, LoginResponse>(Dispatchers.IO) {
    override suspend fun execute(parameters: AuthRequest): LoginResponse {
        val loginUser = authRepository.loginUser(parameters)
        loginUser?.let {
            return loginUser
        }
        return LoginResponse("")
    }
}