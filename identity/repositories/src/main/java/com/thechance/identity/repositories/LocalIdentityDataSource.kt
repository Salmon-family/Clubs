package com.thechance.identity.repositories

interface LocalIdentityDataSource {

    suspend fun getFirstInstallValue(): Boolean

    suspend fun saveFirstInstallValue(value: Boolean): Boolean

}