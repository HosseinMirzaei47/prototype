package com.example.prototype.features.users.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.PagingData
import com.example.prototype.features.users.data.User
import com.example.prototype.features.users.data.UsersRepository
import com.example.prototype.features.users.domain.GetUsersUseCase

class UsersViewModel @ViewModelInject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val usersRepository: UsersRepository
) : ViewModel() {

    val users = liveData<PagingData<User>> { getUsersUseCase().data }

}