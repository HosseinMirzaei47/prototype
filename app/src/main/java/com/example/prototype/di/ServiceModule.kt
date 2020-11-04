package com.example.prototype.di

import androidx.lifecycle.MutableLiveData
import com.example.prototype.features.auth.service.AuthApi
import com.example.prototype.features.users.services.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ServiceModule {

    private val responseStatus = MutableLiveData<Response>()
    private var logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(Interceptor { chain ->
            val proceed = chain.proceed(request = chain.request())
            responseStatus.postValue(proceed)
            proceed
        })
        .addNetworkInterceptor(logging)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit): UserApi {
        return retrofit
            .create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthApi {
        return retrofit
            .create(AuthApi::class.java)
    }

}