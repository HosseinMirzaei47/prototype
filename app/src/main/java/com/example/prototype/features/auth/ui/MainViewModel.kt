package com.example.prototype.features.auth.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.prototype.features.auth.domain.GetTokenUseCase

class MainViewModel @ViewModelInject constructor(
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {
    suspend fun getToken() = getTokenUseCase().data
}