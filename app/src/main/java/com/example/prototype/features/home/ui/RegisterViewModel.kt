package com.example.prototype.features.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prototype.core.resource.Resource
import com.example.prototype.features.home.data.AuthRepository
import com.example.prototype.features.home.data.LoginResponse

class RegisterViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerResult = MutableLiveData<Resource<LoginResponse>>()
    val registerResult: MutableLiveData<Resource<LoginResponse>> get() = _registerResult

    fun registerUser() {
        _registerResult.value = Resource.loading(null)

    }

}