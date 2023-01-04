package com.thechance.clubs.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.common.local.daos.ChatDao
import com.common.local.daos.ClubDao
import com.common.local.ClubDataBase
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
    fun providesClubDataBase(
        @ApplicationContext context: Context,
    ): ClubDataBase {
        return Room.databaseBuilder(context, ClubDataBase::class.java, "ClubDatabase")
            .build()
    }

    @Singleton
    @Provides
    fun provideClubDao(clubDataBase: ClubDataBase): ClubDao {
        return clubDataBase.clubDao()
    }

    @Singleton
    @Provides
    fun provideChatDao(clubDataBase: ClubDataBase): ChatDao {
        return clubDataBase.chatDao()
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
