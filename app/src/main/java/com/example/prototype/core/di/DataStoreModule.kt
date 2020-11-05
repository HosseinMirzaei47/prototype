package com.example.prototype.core.di

import com.example.prototype.MyApplication
import com.example.prototype.core.storage.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataStoreModule {

    @Singleton
    @Provides
    fun provideSharedPref(): Settings {
        return Settings(MyApplication.app)
    }

}