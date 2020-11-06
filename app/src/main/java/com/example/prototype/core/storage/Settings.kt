package com.example.prototype.core.storage

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Settings(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = DATA_STORE_NAME)

    suspend fun storeUserToken(userToken: String?) = dataStore.edit { preferences ->
        preferences[KEY_TOKEN] = userToken ?: NO_TOKEN
    }

    suspend fun setNextDestinationFlag(flag: Boolean) = dataStore.edit { preferences ->
        preferences[KEY_NEXT_DESTINATION] = flag
    }

    suspend fun clearStorage() = dataStore.edit { it.clear() }

    val nextDestinationFlag: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[KEY_NEXT_DESTINATION] ?: false
    }

    val userTokenFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[KEY_TOKEN] ?: NO_TOKEN
    }

    companion object Key {
        const val DATA_STORE_NAME = "settings"
        val KEY_TOKEN = preferencesKey<String>("TOKEN_KEY")
        val KEY_NEXT_DESTINATION = preferencesKey<Boolean>("NEXT_DESTINATION_KEY")
        const val NO_TOKEN = ""
    }
}