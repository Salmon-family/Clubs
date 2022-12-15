package com.thechance.clubs.di

import com.devfalah.remote.RemoteDataSourceImp
import com.devfalah.repositories.LocalDataSource
import com.devfalah.repositories.RemoteDataSource
import com.thechance.identity.remote.IdentityDataSourceImp
import com.thechance.identity.repositories.IdentityDataSource
import com.thechance.local.LocalDataSourceImp
import com.thechance.remote.ChatDataSourceImp
import com.thechance.repositories.ChatDataSource
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

    @Binds
    abstract fun bindChatDataSource(
        chatDataSourceImp: ChatDataSourceImp
    ): ChatDataSource

    @Binds
    abstract fun bindIdentityDataSource(
        identityDataSourceImp: IdentityDataSourceImp
    ): IdentityDataSource

    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImp: LocalDataSourceImp
    ): LocalDataSource
}