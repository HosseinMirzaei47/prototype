package com.example.prototype.features.users.data

import com.example.prototype.core.utils.safeApiCall
import com.example.prototype.features.users.services.UserApi
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
    private val service: UserApi
) {

    suspend fun getUsers() = safeApiCall { service.getUsers(page = "2") }

}