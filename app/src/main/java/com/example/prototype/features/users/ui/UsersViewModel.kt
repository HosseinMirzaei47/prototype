package com.example.prototype.features.users.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototype.core.resource.Resource
import com.example.prototype.features.users.data.User
import com.example.prototype.features.users.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private var _allUsersResult = MutableLiveData<Resource<List<User>>>()
    val allUsersResult: LiveData<Resource<List<User>>> get() = _allUsersResult

    fun getAllUsers() {
        _allUsersResult.value = Resource.loading(null)

        viewModelScope.launch(Dispatchers.IO) {
            _allUsersResult.postValue(userRepository.getAllUsers())
        }
    }

}