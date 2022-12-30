package com.thechance.clubs.di

import com.common.local.dataSource.ChatLocalDataSourceImp
import com.common.local.dataSource.CoreLocalDataSourceImp
import com.devfalah.firebase.ChatFirebaseDataSourceImp
import com.devfalah.firebase.CoreFireStoreDataSourceImpl
import com.devfalah.firebase.IdentityFirebaseDataSourceImp
import com.devfalah.local.ChatDataStoreDataSourceImp
import com.devfalah.remote.RemoteDataSourceImp
import com.devfalah.repositories.CoreLocalDataSource
import com.devfalah.repositories.CoreFireStoreDataSource
import com.devfalah.repositories.RemoteDataSource
import com.devfalah.repository.ChatDataStoreDataSource
import com.devfalah.repository.ChatFirebaseDataSource
import com.devfalah.repository.ChatLocalDataSource
import com.devfalah.repository.ChatRemoteDataSource
import com.thechance.identity.remote.IdentityDataSourceImp
import com.thechance.identity.repositories.IdentityFirebaseDataSource
import com.thechance.identity.repositories.RemoteIdentityDataSource
import com.thechance.remote.ChatDataSourceImp
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
        chatLocalDataSourceImp: ChatLocalDataSourceImp
    ): ChatLocalDataSource

    @Binds
    abstract fun bindChatFirebaseDataSource(
        chatDataSource: ChatFirebaseDataSourceImp
    ): ChatFirebaseDataSource

    @Binds
    abstract fun bindClubLocalDataSource(
        localDataSourceImp: CoreLocalDataSourceImp
    ): CoreLocalDataSource


    @Binds
    abstract fun bindIdentityFirebaseDataSource(
        identityFirebaseDataSource: IdentityFirebaseDataSourceImp
    ): IdentityFirebaseDataSource

    @Binds
    abstract fun bindCoreFireStoreDataSource(
        coreFireStoreDataSourceImpl: CoreFireStoreDataSourceImpl
    ): CoreFireStoreDataSource


    @Binds
    abstract fun bindChatDataStoreDataSource(
        chatDataStoreDataSourceImp: ChatDataStoreDataSourceImp,
    ): ChatDataStoreDataSource

}