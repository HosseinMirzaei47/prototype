package com.example.prototype.features.auth.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.prototype.features.auth.domain.GetNextDestinationFlagUseCase
import com.example.prototype.features.auth.domain.GetTokenUseCase
import com.example.prototype.features.auth.domain.RequestSetNextDestinationFlagUseCase

class MainViewModel @ViewModelInject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getNextDestinationFlagUseCase: GetNextDestinationFlagUseCase,
    private val requestSetNextDestinationFlagUseCase: RequestSetNextDestinationFlagUseCase
) : ViewModel() {
    suspend fun getToken() = getTokenUseCase().data

    suspend fun getNextDestinationFlag() = getNextDestinationFlagUseCase().data

    suspend fun setNextDestinationFlag(flag: Boolean) = requestSetNextDestinationFlagUseCase(flag)
}