package com.example.prototype.core.di

import com.example.prototype.MyApplication
import com.example.prototype.features.auth.data.AuthApi
import com.example.prototype.features.users.data.UserApi
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ServiceModule {

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(FlipperOkhttpInterceptor(MyApplication.networkFlipperPlugin))
        .addInterceptor { chain ->
            val newBuilder = chain.request().newBuilder()
            val request = newBuilder.build()
            chain.proceed(request)

        }
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