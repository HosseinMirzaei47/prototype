package com.example.prototype.features.second.ui.domain

import androidx.datastore.preferences.Preferences
import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.core.utils.CoroutineUseCaseNoParameter
import com.example.prototype.features.auth.data.AuthRepository
import javax.inject.Inject

class RequestLogoutUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    dispatchers: CoroutineDispatchers
) : CoroutineUseCaseNoParameter<Preferences>(dispatchers.io) {
    override suspend fun execute(): Preferences {
        return authRepository.logOutUser()
    }
}