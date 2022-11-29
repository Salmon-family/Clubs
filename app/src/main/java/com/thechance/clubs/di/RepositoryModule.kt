package com.thechance.clubs.di

import com.devfalah.repositories.AuthRepositoryImp
import com.devfalah.usecases.AuthRepository
import com.nadafeteiha.usecases.ChatRepository
import com.thechance.repositories.ChatRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImp
    ): AuthRepository


    @ViewModelScoped
    @Binds
    abstract fun bindChatRepository(
        chatRepositoryImp: ChatRepositoryImp
    ): ChatRepository
}


