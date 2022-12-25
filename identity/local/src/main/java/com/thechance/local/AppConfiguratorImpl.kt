package com.thechance.local

import com.thechance.identity.entities.Club
import com.thechance.identity.repositories.LocalIdentityDataSource
import javax.inject.Inject

class AppConfiguratorImpl @Inject constructor(
    private val dataStorePreferences: DataStorePreferences
) : LocalIdentityDataSource {

    companion object DataStorePreferencesKeys {
        const val SIGN_UP_STATE_KEY = "sign_up_state_key"
        const val START_INSTALL_STATE_KEY = "start_install_state_key"
        const val TOKEN_KEY = "token_key"
    }

    override fun getStartInstall(): Boolean? {
        return dataStorePreferences.readBoolean(START_INSTALL_STATE_KEY)
    }

    override suspend fun setStartInstall(value: Boolean) {
        return dataStorePreferences.writeBoolean(START_INSTALL_STATE_KEY, value)
    }

    override fun getUserId(): String? {
        return dataStorePreferences.readString(SIGN_UP_STATE_KEY)
    }

    override suspend fun saveUserId(id: String) {
        return dataStorePreferences.writeString(SIGN_UP_STATE_KEY, id)
    }

    override fun getClubs(): List<Club> {
        return Clubs().getClubs()
    }

    override suspend fun saveToken(token: String) {
        dataStorePreferences.writeString(TOKEN_KEY,token)
    }

    override fun getToken(): String {
        return dataStorePreferences.readString(TOKEN_KEY)?: ""
    }
}