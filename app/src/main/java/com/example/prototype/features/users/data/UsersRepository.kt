package com.example.prototype.features.users.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource
) {

    fun getUsers() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 15,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UsersPagingSource(usersRemoteDataSource) }
        ).flow

}