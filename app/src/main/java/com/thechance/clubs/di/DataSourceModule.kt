package com.thechance.clubs.di

import com.devfalah.remote.RemoteDataSourceImp
import com.devfalah.repository.AuthRepositoryImp
import com.devfalah.repository.RemoteDataSource
import com.devfalah.usecases.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(
        repositoryImp: RemoteDataSourceImp
    ): RemoteDataSource
}