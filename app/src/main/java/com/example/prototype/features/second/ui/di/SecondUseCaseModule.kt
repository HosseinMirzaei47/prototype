package com.example.prototype.features.second.ui.di

import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.features.auth.data.AuthRepository
import com.example.prototype.features.second.ui.domain.RequestLogoutUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object SecondUseCaseModule {

    @Singleton
    @Provides
    fun provideLogoutUseCase(
        authRepository: AuthRepository,
        coroutineDispatchers: CoroutineDispatchers
    ): RequestLogoutUserUseCase {
        return RequestLogoutUserUseCase(authRepository, coroutineDispatchers)
    }

}