package com.thechance.clubs.di

import android.content.Context
import androidx.room.Room
import com.thechance.local.ClubDao
import com.thechance.local.ClubDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClubDataBaseModule {

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
}