package com.example.prototype.features.users.data

import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {

    suspend fun getAllUsers(): AllUsersResponse? {
        val request = userRemoteDataSource.getAllUsers()
        return request.data
    }

}