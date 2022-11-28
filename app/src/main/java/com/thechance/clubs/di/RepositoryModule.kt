package com.thechance.clubs.di

import com.thechance.remote.ClubDataSource
import com.thechance.repository.RemoteDataSource
import com.thechance.repository.RepositoryImp
import com.thechance.usecase.Repository
import com.thechance.usecase.GetUserDetailsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAppRepository(repositoryImp: RepositoryImp): Repository

    @Singleton
    @Binds
    abstract fun bindAppRemote(clubDataSource: ClubDataSource): RemoteDataSource

}

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun bindUseCase(
        repositoryImp: RepositoryImp
    ): GetUserDetailsUseCase {
        return GetUserDetailsUseCase(repositoryImp)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object RemoteModule {

    @ViewModelScoped
    @Provides
    fun bindRemote(
        dataSource: ClubDataSource
    ): RepositoryImp {
        return RepositoryImp(dataSource)
    }
}