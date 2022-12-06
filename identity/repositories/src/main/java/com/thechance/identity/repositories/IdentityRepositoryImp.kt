package com.thechance.identity.repositories

import com.thechance.identity.entities.Account
import com.thechance.identity.entities.UserData
import com.thechance.identity.repositories.mappers.MapperUserDataDTOToUserData
import com.thechance.identity.repositories.mappers.toEntity
import com.thechance.identity.usecases.IdentityRepository
import javax.inject.Inject

class IdentityRepositoryImp @Inject constructor(
    private val remoteDataSource: IdentityDataSource,
    private val mapperUserDataDTOToUserData: MapperUserDataDTOToUserData
) : IdentityRepository {

    override suspend fun login(userName: String, password: String): Boolean {
        return remoteDataSource.login(userName, password)//.toEntity()
    }

    override suspend fun signup(userData: UserData): Account {
        return remoteDataSource.signup(mapperUserDataDTOToUserData.map(userData)).toEntity()
    }
}