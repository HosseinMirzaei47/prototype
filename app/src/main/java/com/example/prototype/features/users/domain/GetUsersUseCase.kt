package com.example.prototype.features.users.domain

import androidx.paging.PagingData
import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.core.utils.CoroutineUseCaseNoParameter
import com.example.prototype.features.users.data.User
import com.example.prototype.features.users.data.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    dispatcher: CoroutineDispatchers
) : CoroutineUseCaseNoParameter<Flow<PagingData<User>>>(dispatcher.io) {
    override suspend fun execute(): Flow<PagingData<User>> {
        return usersRepository.getUsers()
    }
}