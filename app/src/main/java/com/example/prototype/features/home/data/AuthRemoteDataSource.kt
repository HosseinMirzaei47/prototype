package com.example.prototype.features.home.data

import com.example.prototype.core.utils.safeApiCall
import com.example.prototype.features.home.services.AuthApi
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val service: AuthApi
) {

    suspend fun loginUser(authRequest: AuthRequest) =
        safeApiCall { service.loginUser(authRequest = authRequest) }

    suspend fun registerUser(authRequest: AuthRequest) =
        safeApiCall { service.registerUser(authRequest = authRequest) }

}