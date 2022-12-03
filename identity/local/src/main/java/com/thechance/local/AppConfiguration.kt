package com.thechance.local

import javax.inject.Inject

interface AppConfiguration {

    fun getSignupState()
    suspend fun setSignupState(value: String)
}

class AppConfigurator @Inject constructor(
    private val dataStorePreferences: DataStorePreferences
) : AppConfiguration {
    override fun getSignupState() {
        dataStorePreferences.readString(SIGN_UP_STATE_KEY)
    }

    override suspend fun setSignupState(value: String) {
        dataStorePreferences.writeString(SIGN_UP_STATE_KEY, value)
    }


    companion object DataStorePreferencesKeys {
        const val SIGN_UP_STATE_KEY = "sign_up_state_key"
    }
}