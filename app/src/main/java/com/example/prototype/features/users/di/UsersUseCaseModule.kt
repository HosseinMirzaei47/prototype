package com.example.prototype.features.users.di

import com.example.prototype.core.CoroutineDispatchers
import com.example.prototype.features.users.data.UsersRepository
import com.example.prototype.features.users.domain.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object UsersUseCaseModule {

    @Singleton
    @Provides
    fun provideGetUsersUseCase(
        usersRepository: UsersRepository,
        dispatchers: CoroutineDispatchers
    ): GetUsersUseCase {
        return GetUsersUseCase(usersRepository, dispatchers)
    }

}