package com.thechance.clubs.di

import com.devfalah.repositories.AuthRepositoryImp
import com.devfalah.repositories.RemoteDataSource
import com.devfalah.usecases.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

//@Module
//@InstallIn(ViewModelComponent::class)
//abstract class RepositoryModule {
//
//    @ViewModelScoped
//    @Binds
//    abstract fun bindAuthRepository(
//        authRepository: AuthRepositoryImp
//    ): AuthRepository
//}


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesAuthRepository(
        remoteDataSource: RemoteDataSource,
    ): AuthRepository{
        return AuthRepositoryImp(remoteDataSource)
    }
}