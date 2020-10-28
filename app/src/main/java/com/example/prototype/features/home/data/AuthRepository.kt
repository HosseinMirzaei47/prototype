package com.example.prototype.features.home.data

import com.example.prototype.core.resource.Resource
import com.example.prototype.core.resource.Status
import com.example.prototype.core.storage.data.Settings
import com.example.prototype.features.home.services.AuthApi
import com.example.prototype.core.utils.safeApiCall
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sharedPreference: Settings,
    private val service: AuthApi
) {

    suspend fun loginUser(authRequest: AuthRequest): Resource<LoginResponse> {

        var resource: Resource<LoginResponse> = Resource<LoginResponse>(Status.ERROR, null, null)

        val request = safeApiCall { service.loginUser(authRequest = authRequest) }

        if (request.status == Status.SUCCESS) {

            val token = request.data?.token ?: return Resource.error(
                "Authentication failed",
                request.data
            )

            resource = Resource.success(LoginResponse(token))
            sharedPreference.authToken = token
        }
        return resource
    }

    suspend fun registerUser(authRequest: AuthRequest): Resource<RegisterResponse> {
        var resource = Resource<RegisterResponse>(Status.ERROR, null, null)

        val request = safeApiCall { service.registerUser(authRequest = authRequest) }

        if (request.status == Status.SUCCESS) {
            val token = request.data?.token ?: return Resource.error(
                "Authentication Failed",
                null
            )

            resource = Resource.success(RegisterResponse(request.data.id, token))
            sharedPreference.authToken = token
        }

        return resource
    }

}