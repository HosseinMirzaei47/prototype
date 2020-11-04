package com.example.prototype.di

import com.example.prototype.core.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DispatchersModule {

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatchers =
        CoroutineDispatchers(Dispatchers.Main, Dispatchers.IO, Dispatchers.Default)

}