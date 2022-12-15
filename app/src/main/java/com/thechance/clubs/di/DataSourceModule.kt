package com.thechance.clubs.di

import com.devfalah.firebase.ChatFirebaseDataSourceImp
import com.devfalah.local.LocalDataSourceImp
import com.devfalah.remote.RemoteDataSourceImp
import com.devfalah.repositories.RemoteDataSource
import com.thechance.identity.remote.IdentityDataSourceImp
import com.thechance.identity.repositories.RemoteIdentityDataSource
import com.thechance.remote.ChatDataSourceImp
import com.devfalah.repository.ChatRemoteDataSource
import com.devfalah.repository.ChatFirebaseDataSource
import com.devfalah.repository.ChatLocalDataSource
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
    ): ChatRemoteDataSource

    @Binds
    abstract fun bindIdentityDataSource(
        identityDataSourceImp: IdentityDataSourceImp
    ): RemoteIdentityDataSource

    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImp: LocalDataSourceImp
    ): ChatLocalDataSource

    @Binds
    abstract fun bindChatFirebaseDataSource(
        chatDataSource: ChatFirebaseDataSourceImp
    ): ChatFirebaseDataSource

}