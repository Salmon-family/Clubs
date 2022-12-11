package com.thechance.clubs.di

import com.thechance.identity.repositories.LocalIdentityDataSource
import com.thechance.local.AppConfiguratorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppConfigurationModule {

    @Singleton
    @Binds
    abstract fun bindAppConfiguration(
        appConfiguratorImpl: AppConfiguratorImpl
    ): LocalIdentityDataSource
}