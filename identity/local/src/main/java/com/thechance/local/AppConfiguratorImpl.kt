package com.thechance.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.thechance.identity.entities.Club
import com.thechance.identity.repositories.LocalIdentityDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AppConfiguratorImpl @Inject constructor(
    private val userDataStore: DataStore<Preferences>,
) : LocalIdentityDataSource {

    companion object DataStorePreferencesKeys {
        const val SIGN_UP_STATE_KEY = "sign_up_state_key"
        const val START_INSTALL_STATE_KEY = "start_install_state_key"
        const val TOKEN_KEY = "token_key"
    }

    override fun getStartInstall(): Boolean? {
        return runBlocking {
            userDataStore.data.map {
                it[booleanPreferencesKey(START_INSTALL_STATE_KEY)]
            }.first()
        }
    }

    override suspend fun setStartInstall(value: Boolean) {
        userDataStore.edit { preferences ->
            preferences[booleanPreferencesKey(START_INSTALL_STATE_KEY)] = value
        }
    }

    override fun getUserId(): String? {
        return runBlocking {
            userDataStore.data.map {
                it[stringPreferencesKey(SIGN_UP_STATE_KEY)]
            }.first()
        }
    }

    override suspend fun saveUserId(id: String) {
        userDataStore.edit { preferences ->
            preferences[stringPreferencesKey(SIGN_UP_STATE_KEY)] = id
        }
    }

    override fun getClubs(): List<Club> {
        return Clubs().getClubs()
    }

    override suspend fun saveToken(token: String) {
        userDataStore.edit { preferences ->
            preferences[stringPreferencesKey(TOKEN_KEY)] = token
        }
    }

    override fun getToken(): String {
        return runBlocking {
            userDataStore.data.map {
                it[stringPreferencesKey(TOKEN_KEY)]
            }.first()
        } ?: ""
    }
}