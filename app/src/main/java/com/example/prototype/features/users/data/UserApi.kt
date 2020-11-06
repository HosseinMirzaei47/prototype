package com.example.prototype.features.users.data

import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(@Query("page") page: String): AllUsersResponse

}