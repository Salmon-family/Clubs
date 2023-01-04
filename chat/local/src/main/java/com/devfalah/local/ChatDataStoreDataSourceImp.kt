package com.devfalah.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
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
                it[intPreferencesKey(USER_ID)]
            }.first()
        }
    }


    companion object {
        private const val USER_ID = "user_id"
    }

}