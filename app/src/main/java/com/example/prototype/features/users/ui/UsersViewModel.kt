package com.example.prototype.features.users.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.prototype.features.users.data.User
import com.example.prototype.features.users.domain.GetUsersUseCase

class UsersViewModel @ViewModelInject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    suspend fun getUsers(): LiveData<PagingData<User>>? = getUsersUseCase().data
}