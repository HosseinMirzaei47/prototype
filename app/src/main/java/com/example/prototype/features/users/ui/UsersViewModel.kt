package com.example.prototype.features.users.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.prototype.features.users.domain.GetUsersUseCase

class UsersViewModel @ViewModelInject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    suspend fun getUsers() = getUsersUseCase().data
}