package com.example.prototype.core.storage.data

import android.content.SharedPreferences
import com.example.prototype.core.storage.types.StringPreference

class Settings(sharedPreferences: SharedPreferences) {

    var authToken: String? by StringPreference(
        sharedPreferences,
        AUTH_TOKEN
    )

    companion object Key {
        const val AUTH_TOKEN = "AUTH_TOKEN"
    }
}