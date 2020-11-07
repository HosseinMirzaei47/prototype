package com.example.prototype.features.auth.di

import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.features.auth.data.AuthRepository
import com.example.prototype.features.auth.domain.RequestLoginUserUseCase
import com.example.prototype.features.auth.domain.RequestRegisterUserUseCase
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

}