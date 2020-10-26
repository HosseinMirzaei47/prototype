package com.example.prototype.features.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototype.core.resource.Resource
import com.example.prototype.features.home.data.AuthRepository
import com.example.prototype.features.home.data.AuthRequest
import com.example.prototype.features.home.data.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: MutableLiveData<Resource<LoginResponse>> get() = _loginResult

    fun loginUser(authRequest: AuthRequest) {
        _loginResult.value = Resource.loading(null)

        viewModelScope.launch(Dispatchers.IO) {
            _loginResult.postValue(authRepository.loginUser(authRequest))
        }
    }

}