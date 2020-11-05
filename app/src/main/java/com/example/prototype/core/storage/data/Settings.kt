package com.example.prototype.core.storage.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Settings(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = DATA_STORE_NAME)

    suspend fun storeUserToken(userToken: String?) = dataStore.edit {
        it[KEY_TOKEN] = userToken ?: NO_TOKEN
    }

    suspend fun clearStorage() {
        dataStore.edit {
            it.clear()
        }
    }

    val userTokenFlow: Flow<String> = dataStore.data.map {
        it[KEY_TOKEN] ?: NO_TOKEN
    }

    companion object Key {
        const val DATA_STORE_NAME = "settings"
        val KEY_TOKEN = preferencesKey<String>("TOKEN_KEY")
        const val NO_TOKEN = ""
    }
}