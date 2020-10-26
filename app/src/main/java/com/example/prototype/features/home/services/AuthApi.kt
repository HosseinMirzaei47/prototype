package com.example.prototype.features.home.services

import com.example.prototype.features.home.data.AuthRequest
import com.example.prototype.features.home.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("register")
    suspend fun loginUser(@Body authRequest: AuthRequest): LoginResponse


}