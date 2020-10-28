package com.example.prototype.di

import androidx.lifecycle.MutableLiveData
import com.example.prototype.features.home.services.AuthApi
import com.example.prototype.features.users.services.UserApi
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    /*@Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
    }*/

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            /*.add(ApplicationJsonAdapterFactory)*/
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi
    ): Retrofit {
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