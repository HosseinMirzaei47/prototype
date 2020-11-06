package com.example.prototype.features.second.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.prototype.features.second.ui.domain.RequestLogoutUserUseCase

class SecondViewModel @ViewModelInject constructor(
    private val requestLogoutUserUseCase: RequestLogoutUserUseCase
) : ViewModel() {
    suspend fun logoutUser() = requestLogoutUserUseCase().data
}