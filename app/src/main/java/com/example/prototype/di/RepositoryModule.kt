package com.example.prototype.di

import com.example.prototype.core.storage.data.Settings
import com.example.prototype.features.home.data.AuthRepository
import com.example.prototype.features.home.services.AuthApi
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
    fun provideUsersRepository(api: UserApi): UserRepository {
        return UserRepository(
            api
        )
    }

    @Singleton
    @Provides
    fun provideAuthRepository(settings: Settings, api: AuthApi): AuthRepository {
        return AuthRepository(
            settings,
            api
        )
    }

}