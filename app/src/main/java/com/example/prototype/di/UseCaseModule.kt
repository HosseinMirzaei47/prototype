package com.example.prototype.di

import com.example.prototype.features.home.data.AuthRepository
import com.example.prototype.features.home.domain.RequestLoginUserUseCase
import com.example.prototype.features.home.domain.RequestRegisterUserUseCase
import com.example.prototype.features.users.data.UserRepository
import com.example.prototype.features.users.domain.GetUserUseCase
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
        authRepository: AuthRepository
    ): RequestLoginUserUseCase {
        return RequestLoginUserUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideRegisterUserUseCase(
        authRepository: AuthRepository
    ): RequestRegisterUserUseCase {
        return RequestRegisterUserUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideGetUsersUseCase(
        userRepository: UserRepository
    ): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }

}