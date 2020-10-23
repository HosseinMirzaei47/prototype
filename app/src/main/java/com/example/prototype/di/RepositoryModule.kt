package com.example.prototype.di

import android.content.Context
import com.example.prototype.features.users.data.UserRepository
import com.example.prototype.features.users.services.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUsersRepository(
        context: Context,
        api: UserApi
    ): UserRepository {
        return UserRepository(
            context, api
        )
    }

}