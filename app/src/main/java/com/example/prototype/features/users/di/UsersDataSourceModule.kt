package com.example.prototype.features.users.di

import com.example.prototype.features.users.data.UsersRemoteDataSource
import com.example.prototype.features.users.services.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object UsersDataSourceModule {

    @Singleton
    @Provides
    fun provideUsersRemoteDataSource(userApi: UserApi): UsersRemoteDataSource {
        return UsersRemoteDataSource(userApi)
    }

}