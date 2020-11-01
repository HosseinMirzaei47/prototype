package com.example.prototype.features.home.domain

import com.example.prototype.core.CoroutineUseCase
import com.example.prototype.features.home.data.AuthRepository
import com.example.prototype.features.home.data.AuthRequest
import com.example.prototype.features.home.data.LoginResponse
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
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