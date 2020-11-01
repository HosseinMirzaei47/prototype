package com.example.prototype.di

import com.example.prototype.features.home.data.AuthRemoteDataSource
import com.example.prototype.features.home.services.AuthApi
import com.example.prototype.features.users.data.UsersRemoteDataSource
import com.example.prototype.features.users.services.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(
        service: AuthApi
    ): AuthRemoteDataSource {
        return AuthRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideUsersRemoteDataSource(userApi: UserApi): UsersRemoteDataSource {
        return UsersRemoteDataSource(userApi)
    }

}