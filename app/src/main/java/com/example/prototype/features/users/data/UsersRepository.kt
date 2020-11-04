package com.example.prototype.features.users.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource
) {

    suspend fun getAllUsers(): AllUsersResponse? {
        val request = usersRemoteDataSource.getAllUsers()
        return request.data
    }

    fun getAllUsersPaging() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 15,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UsersPagingSource(usersRemoteDataSource) }
        ).liveData


}