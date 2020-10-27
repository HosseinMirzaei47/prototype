package com.example.prototype.features.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototype.core.resource.Resource
import com.example.prototype.features.home.data.AuthRepository
import com.example.prototype.features.home.data.AuthRequest
import com.example.prototype.features.home.data.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerResult = MutableLiveData<Resource<RegisterResponse>>()
    val registerResult: MutableLiveData<Resource<RegisterResponse>> get() = _registerResult

    fun registerUser(authRequest: AuthRequest) {
        _registerResult.value = Resource.loading(null)

        viewModelScope.launch(Dispatchers.IO) {
            _registerResult.postValue(authRepository.registerUser(authRequest = authRequest))
        }
    }

}