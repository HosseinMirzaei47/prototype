package com.example.prototype.features.users.data

import androidx.paging.PagingSource
import retrofit2.HttpException
import javax.inject.Inject

private const val USERS_STARTING_PAGE_INDEX = 1

class UsersPagingSource @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource
) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: USERS_STARTING_PAGE_INDEX

        return try {
            val response = usersRemoteDataSource.getUsers(position.toString())
            val users = response.data?.users

            LoadResult.Page(
                data = if (!users.isNullOrEmpty()) users else listOf(),
                prevKey = if (position == USERS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (users?.isEmpty()!!) null else position + 1
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}