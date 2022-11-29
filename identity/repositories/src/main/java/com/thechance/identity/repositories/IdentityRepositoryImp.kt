package com.thechance.identity.repositories

import com.thechance.identity.entities.User
import com.thechance.identity.repositories.mappers.toEntity
import com.thechance.identity.usecases.IdentityRepository
import javax.inject.Inject

class IdentityRepositoryImp @Inject constructor(
    private val remoteDataSource: IdentityDataSource
) : IdentityRepository {

    override suspend fun login(userName: String, password: String): User {
        return remoteDataSource.login(userName, password).toEntity()
    }
}