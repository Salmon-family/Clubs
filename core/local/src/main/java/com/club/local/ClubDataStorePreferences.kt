package com.club.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ClubDataStorePreferences @Inject constructor(context: Context) {

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        PREFERENCES_FILE_NAME
    )
    private val prefDataStore = context.preferencesDataStore

    suspend fun writeString(key: String, value: String) {
        prefDataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    suspend fun writeBoolean(key: String, value: Boolean) {
        prefDataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    fun readString(key: String): String? {
        return runBlocking { prefDataStore.data.map { it[stringPreferencesKey(key)] }.first() }
    }

    fun readBoolean(key: String): Boolean? {
        return runBlocking { prefDataStore.data.map { it[booleanPreferencesKey(key)] }.first() }
    }

    companion object {
        private const val PREFERENCES_FILE_NAME = "clubs"
    }
}