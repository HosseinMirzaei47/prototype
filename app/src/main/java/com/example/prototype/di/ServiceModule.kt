package com.example.prototype.di

import com.example.prototype.features.users.services.UserApi
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ServiceModule {

    private val responseStatus = MutableLiveData<Response>()
    private var logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(logging)
        .build()

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): UserApi {
        return retrofit
            .build()
            .create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit.Builder): AuthApi {
        return retrofit
            .build()
            .create(AuthApi::class.java)
    }

}