package com.example.prototype.features.users.services

import com.example.prototype.features.users.data.AllUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getAllUsers(@Query("page") page: String): AllUsersResponse

}