package com.club.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.devfalah.repositories.CoreDataStoreDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CoreDataStoreDataSourceImp @Inject constructor(
    private val userDataStore: DataStore<Preferences>,
) : CoreDataStoreDataSource {

    override fun getUserId(): Int? {
        return runBlocking {
            userDataStore.data.map {
                it[intPreferencesKey(USER_ID)]
            }.first()
        }
    }

    override suspend fun saveUserId(userId: Int) {
        userDataStore.edit { preferences ->
            preferences[intPreferencesKey(USER_ID)] = userId
        }
    }

    override fun isUserLoggedIn(): Boolean {
        return runBlocking {
            userDataStore.data.map {
                it[booleanPreferencesKey(SIGN_UP_STATE_KEY)]
            }.first()
        } ?:false
    }

    override suspend fun deleteUserId() {
        userDataStore.edit { preferences ->
            preferences[booleanPreferencesKey(SIGN_UP_STATE_KEY)] = false
        }

    }

    override fun getLanguage(): String? {
        return runBlocking {
            userDataStore.data.map {
                it[stringPreferencesKey(LANGUAGE_KEY)]
            }.first()
        }
    }

    override suspend fun saveLanguage(language: String) {
        userDataStore.edit { preferences ->
            preferences[stringPreferencesKey(LANGUAGE_KEY)] = language
        }
    }

    companion object {
        private const val LANGUAGE_KEY = "language_key"
        private const val USER_ID = "user_id"
        private const val SIGN_UP_STATE_KEY = "sign_up_state_key"
    }
}