package com.thechance.clubs.di

import android.content.Context
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
    fun provideChatDao(movieDataBase: ChatDataBase): ChatDao {
        return movieDataBase.chatDao()
    }
}
