package com.example.prototype.features.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototype.core.resource.Resource
import com.example.prototype.features.home.data.AuthRequest
import com.example.prototype.features.home.data.LoginResponse
import com.example.prototype.features.home.domain.RequestLoginUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val requestLoginUseCase: RequestLoginUserUseCase
) : ViewModel() {

    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: MutableLiveData<Resource<LoginResponse>> get() = _loginResult

    fun loginUser(authRequest: AuthRequest) {
        _loginResult.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            val response = requestLoginUseCase(authRequest)
            val token = response.data?.token

            if (token.isNullOrEmpty().not()) {
                _loginResult.postValue(response)
            } else {
                _loginResult.postValue(Resource.error("Login Failed", null))
            }
        }
    }

}