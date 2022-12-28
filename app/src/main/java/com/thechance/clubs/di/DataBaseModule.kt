package com.thechance.clubs.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.devfalah.local.ChatDao
import com.devfalah.local.ChatDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providesChatDataBase(
        @ApplicationContext context: Context,
    ): ChatDataBase {
        return Room.databaseBuilder(context, ChatDataBase::class.java, "ChatDatabase")
            .build()
    }


    @Singleton
    @Provides
    fun provideChatDao(chatDataBase: ChatDataBase): ChatDao {
        return chatDataBase.chatDao()
    }


    @Provides
    @Singleton
    fun provideUserDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            applicationContext.preferencesDataStoreFile("com.thechance.clubs.user_preferences")
        }
    }
}
