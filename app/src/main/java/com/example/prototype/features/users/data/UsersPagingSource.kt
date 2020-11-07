package com.example.prototype.features.users.data

import androidx.paging.PagingSource
import retrofit2.HttpException
import javax.inject.Inject

class UsersPagingSource @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource
) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: 1

        return try {
            val response = usersRemoteDataSource.getUsers(position.toString())
            val users = response.data?.users

            LoadResult.Page(
                data = if (!users.isNullOrEmpty()) users else listOf(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (users?.isEmpty()!!) null else position + 1
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}