package com.example.prototype.di

import com.example.prototype.features.home.data.AuthRepository
import com.example.prototype.features.home.ui.LoginViewModel
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
    ): LoginViewModel.LoginUser {
        return LoginViewModel.LoginUser(authRepository)
    }

}