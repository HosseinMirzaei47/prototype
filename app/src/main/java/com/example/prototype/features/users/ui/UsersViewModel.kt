package com.example.prototype.features.users.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototype.core.resource.Resource
import com.example.prototype.features.users.data.AllUsersResponse
import com.example.prototype.features.users.domain.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel @ViewModelInject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var _allUsersResult = MutableLiveData<Resource<AllUsersResponse>>()
    val allUsersResult: LiveData<Resource<AllUsersResponse>> get() = _allUsersResult

    fun getAllUsers() {
        _allUsersResult.value = Resource.loading(null)

        viewModelScope.launch(Dispatchers.IO) {
            val userUseCase = getUserUseCase()
            userUseCase.data?.let { response ->
                if (response.total_pages < 0) {
                    _allUsersResult.value = Resource.error("Something went wrong", null)
                } else {
                    _allUsersResult.postValue(userUseCase)
                }
            }
        }
    }

}