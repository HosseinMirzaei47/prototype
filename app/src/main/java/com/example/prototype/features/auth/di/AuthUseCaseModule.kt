package com.example.prototype.features.auth.di

import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.features.auth.data.AuthRepository
import com.example.prototype.features.auth.domain.GetNextDestinationFlagUseCase
import com.example.prototype.features.auth.domain.RequestLoginUserUseCase
import com.example.prototype.features.auth.domain.RequestRegisterUserUseCase
import com.example.prototype.features.auth.domain.RequestSetNextDestinationFlagUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AuthUseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository,
        coroutineDispatchers: CoroutineDispatchers
    ): RequestLoginUserUseCase {
        return RequestLoginUserUseCase(authRepository, coroutineDispatchers)
    }

    @Singleton
    @Provides
    fun provideRegisterUserUseCase(
        authRepository: AuthRepository,
        coroutineDispatchers: CoroutineDispatchers
    ): RequestRegisterUserUseCase {
        return RequestRegisterUserUseCase(authRepository, coroutineDispatchers)
    }

    @Singleton
    @Provides
    fun provideGetNextDestinationFlagUseCase(
        authRepository: AuthRepository,
        coroutineDispatchers: CoroutineDispatchers
    ): GetNextDestinationFlagUseCase {
        return GetNextDestinationFlagUseCase(authRepository, coroutineDispatchers)
    }

    @Singleton
    @Provides
    fun provideRequestSetNextDestinationFlagUseCase(
        authRepository: AuthRepository,
        coroutineDispatchers: CoroutineDispatchers
    ): RequestSetNextDestinationFlagUseCase {
        return RequestSetNextDestinationFlagUseCase(authRepository, coroutineDispatchers)
    }

}