package com.example.prototype.features.auth.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.prototype.features.auth.data.AuthRepository

class MainViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun hasToken() = authRepository.hasToken()
}