package com.example.prototype.features.users.data

import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource
) {

    suspend fun getAllUsers(): AllUsersResponse? {
        val request = usersRemoteDataSource.getAllUsers()
        return request.data
    }

}