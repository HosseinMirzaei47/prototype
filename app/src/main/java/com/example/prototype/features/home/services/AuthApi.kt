package com.example.prototype.features.home.services

import com.example.prototype.features.home.data.AuthRequest
import com.example.prototype.features.home.data.LoginResponse
import com.example.prototype.features.home.data.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("login")
    suspend fun loginUser(@Body authRequest: AuthRequest): LoginResponse

    @POST("register")
    suspend fun registerUser(@Body authRequest: AuthRequest): RegisterResponse
}