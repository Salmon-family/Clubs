package com.thechance.clubs.di

import com.devfalah.repository.AuthRepositoryImp
import com.devfalah.usecases.AuthRepository
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
}