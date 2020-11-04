package com.example.prototype.features.auth.service

import com.example.prototype.features.auth.data.AuthRequest
import com.example.prototype.features.auth.data.LoginResponse
import com.example.prototype.features.auth.data.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("login")
    suspend fun loginUser(@Body authRequest: AuthRequest): LoginResponse

    @POST("register")
    suspend fun registerUser(@Body authRequest: AuthRequest): RegisterResponse
}