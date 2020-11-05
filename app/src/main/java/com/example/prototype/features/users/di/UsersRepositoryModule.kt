package com.example.prototype.features.users.di

import com.example.prototype.features.users.data.UsersRemoteDataSource
import com.example.prototype.features.users.data.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object UsersRepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        usersRemoteDataSource: UsersRemoteDataSource
    ): UsersRepository {
        return UsersRepository(
            usersRemoteDataSource
        )
    }

}