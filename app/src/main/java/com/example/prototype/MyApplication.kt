package com.example.prototype

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        lateinit var app: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        /*Stetho.initializeWithDefaults(this)*/
    }
}