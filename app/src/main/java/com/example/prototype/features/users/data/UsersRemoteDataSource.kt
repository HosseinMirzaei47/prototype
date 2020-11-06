package com.example.prototype.features.users.data

import com.example.prototype.core.utils.safeApiCall
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
    private val service: UserApi
) {
    suspend fun getUsers(page: String) = safeApiCall { service.getUsers(page = page) }
}