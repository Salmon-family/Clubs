package com.thechance.clubs.di

import com.devfalah.repositories.ClubRepositoryImp
import com.devfalah.usecases.repository.ClubRepository
import com.nadafeteiha.usecases.ChatRepository
import com.thechance.identity.repositories.IdentityRepositoryImp
import com.thechance.identity.usecases.IdentityRepository
import com.devfalah.repository.ChatRepositoryImp
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
        authRepository: ClubRepositoryImp
    ): ClubRepository


    @ViewModelScoped
    @Binds
    abstract fun bindChatRepository(
        chatRepositoryImp: com.devfalah.repository.ChatRepositoryImp
    ): ChatRepository

    @ViewModelScoped
    @Binds
    abstract fun bindIdentityRepository(
        identityRepositoryImp: IdentityRepositoryImp
    ): IdentityRepository
}


