package com.example.prototype.core.utils

object ServiceBuilder {
    /*private val responseStatus = MutableLiveData<Response>()

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(Interceptor { chain ->
            val proceed = chain.proceed(request = chain.request())
            responseStatus.postValue(proceed)
            proceed
        })
        .addInterceptor { chain ->
            val newBuilder = chain.request().newBuilder()
            val request = newBuilder.build()
            chain.proceed(request)
        }
        .addNetworkInterceptor(StethoInterceptor())
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }*/
}
