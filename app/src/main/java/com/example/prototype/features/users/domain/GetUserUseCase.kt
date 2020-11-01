package com.example.prototype.features.users.domain

import com.example.prototype.core.CoroutineUseCaseNoParameter
import com.example.prototype.features.users.data.Ad
import com.example.prototype.features.users.data.AllUsersResponse
import com.example.prototype.features.users.data.UserRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : CoroutineUseCaseNoParameter<AllUsersResponse>(Dispatchers.IO) {
    override suspend fun execute(): AllUsersResponse {
        val allUsers = userRepository.getAllUsers()
        allUsers?.let {
            return allUsers
        }
        return AllUsersResponse(Ad("", "", ""), listOf(), -1, -1, -1, -1)
    }
}