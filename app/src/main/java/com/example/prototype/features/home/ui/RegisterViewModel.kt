package com.example.prototype.features.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototype.core.resource.Resource
import com.example.prototype.features.home.data.AuthRequest
import com.example.prototype.features.home.data.RegisterResponse
import com.example.prototype.features.home.domain.RegisterUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _registerResult = MutableLiveData<Resource<RegisterResponse>>()
    val registerResult: MutableLiveData<Resource<RegisterResponse>> get() = _registerResult

    fun registerUser(authRequest: AuthRequest) {
        _registerResult.value = Resource.loading(null)

        viewModelScope.launch(Dispatchers.IO) {
            val response = registerUserUseCase(authRequest)
            val token = response.data?.token

            if (token.isNullOrEmpty().not()) {
                _registerResult.postValue(response)
            } else {
                _registerResult.postValue(Resource.error("Registration Failed", null))
            }
        }
    }

}