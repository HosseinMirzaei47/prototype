package com.example.prototype.features.auth.di

import com.example.prototype.features.auth.data.AuthApi
import com.example.prototype.features.auth.data.AuthRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AuthDataSourceModule {

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(
        service: AuthApi
    ): AuthRemoteDataSource {
        return AuthRemoteDataSource(service)
    }

}