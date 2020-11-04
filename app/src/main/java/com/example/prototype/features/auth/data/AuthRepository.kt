package com.example.prototype.features.auth.data

import com.example.prototype.core.resource.Resource
import com.example.prototype.core.resource.Status
import com.example.prototype.core.storage.data.Settings
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sharedPreference: Settings,
    private val authRemoteDataSource: AuthRemoteDataSource
) {

    suspend fun loginUser(authRequest: AuthRequest): LoginResponse? {
        var resource: Resource<LoginResponse> = Resource<LoginResponse>(Status.ERROR, null, null)
        val request = authRemoteDataSource.loginUser(authRequest)

        if (request.status == Status.SUCCESS) {
            val token = request.data?.token
            resource = Resource.success(token?.let { LoginResponse(it) })
            sharedPreference.authToken = token
        }
        return resource.data
    }

    suspend fun registerUser(authRequest: AuthRequest): RegisterResponse? {
        var resource = Resource<RegisterResponse>(Status.ERROR, null, null)
        val request = authRemoteDataSource.registerUser(authRequest)

        if (request.status == Status.SUCCESS) {
            resource = Resource.success(request.data?.let { RegisterResponse(it.id, it.token) })
        }
        sharedPreference.authToken = request.data?.token

        return resource.data
    }

}