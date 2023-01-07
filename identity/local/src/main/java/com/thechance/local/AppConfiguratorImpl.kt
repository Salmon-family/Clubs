package com.thechance.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
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
        const val USER_ID = "user_id"
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

    override suspend fun getUserId(): Int? {
        return userDataStore.data.map { it[intPreferencesKey(USER_ID)] }.first()
    }

    override suspend fun saveUserId(id: Int) {
        userDataStore.edit { preferences ->
            preferences[intPreferencesKey(USER_ID)] = id
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

    override suspend fun saveUserAuthState(isLoggedIn: Boolean) {
        userDataStore.edit { preferences ->
            preferences[booleanPreferencesKey(SIGN_UP_STATE_KEY)] = isLoggedIn
        }
    }


}