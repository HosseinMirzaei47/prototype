package com.example.prototype.di

import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.features.auth.data.AuthRepository
import com.example.prototype.features.auth.domain.RequestLoginUserUseCase
import com.example.prototype.features.auth.domain.RequestRegisterUserUseCase
import com.example.prototype.features.users.data.UsersRepository
import com.example.prototype.features.users.domain.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object UseCaseModule {

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
    fun provideGetUsersUseCase(
        usersRepository: UsersRepository,
        dispatchers: CoroutineDispatchers
    ): GetUsersUseCase {
        return GetUsersUseCase(usersRepository, dispatchers)
    }

}