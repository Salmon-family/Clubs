package com.devfalah.local

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.devfalah.repository.ChatDataStoreDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ChatDataStoreDataSourceImp @Inject constructor(
    private val userDataStore: DataStore<Preferences>
) :ChatDataStoreDataSource{
    override fun getUserId(): Int? {
        return runBlocking {
            userDataStore.data.map {
                it[stringPreferencesKey(SIGN_UP_STATE_KEY)]
            }.first()
        }.toString().toInt()
    }


    companion object {
        private const val SIGN_UP_STATE_KEY = "sign_up_state_key"
    }
}