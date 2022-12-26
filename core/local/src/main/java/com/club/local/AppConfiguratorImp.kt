package com.club.local

import com.devfalah.repositories.AppConfigurator
import javax.inject.Inject

class AppConfiguratorImp @Inject constructor(
    private val clubDataStorePreferences: ClubDataStorePreferences
): AppConfigurator {

    override fun getUserId(): String? {
        return clubDataStorePreferences.readString(SIGN_UP_STATE_KEY)
    }

    companion object{
        const val SIGN_UP_STATE_KEY = "sign_up_state_key"
    }
}