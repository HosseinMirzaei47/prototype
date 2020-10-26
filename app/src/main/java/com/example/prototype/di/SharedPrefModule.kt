package com.example.prototype.di

import com.example.prototype.MyApplication
import com.example.prototype.core.storage.data.PreferenceProperty.Companion.getPreferences
import com.example.prototype.core.storage.data.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object SharedPrefModule {

    @Singleton
    @Provides
    fun provideSharedPref(): Settings {
        return Settings(MyApplication.app.getPreferences())
    }

    @Singleton
    @Provides
    fun provideString(): String {
        return "shit"
    }

}