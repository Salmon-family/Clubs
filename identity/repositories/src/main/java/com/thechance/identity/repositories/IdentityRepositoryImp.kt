package com.thechance.identity.repositories

import com.thechance.identity.entities.Account
import com.thechance.identity.entities.Club
import com.thechance.identity.entities.User
import com.thechance.identity.entities.UserData
import com.thechance.identity.repositories.mappers.MapperUserDataDTOToUserData
import com.thechance.identity.repositories.mappers.toEntity
import com.thechance.identity.usecases.IdentityRepository
import javax.inject.Inject

class IdentityRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteIdentityDataSource,
    private val localIdentityDataSource: LocalIdentityDataSource,
    private val mapperUserDataDTOToUserData: MapperUserDataDTOToUserData,
) : IdentityRepository {

    override fun getStartInstallState(): Boolean? {
        return localIdentityDataSource.getStartInstall()
    }

    override suspend fun setStartInstallState(value: Boolean) {
        return localIdentityDataSource.setStartInstall(value)
    }

    override suspend fun login(userName: String, password: String): User {
        return remoteDataSource.login(userName, password).toEntity()
    }

    override suspend fun signup(userData: UserData): Account {
        return remoteDataSource.signup(mapperUserDataDTOToUserData.map(userData)).toEntity()
    }

    override fun getUserId(): String? {
        return localIdentityDataSource.getUserId()
    }

    override suspend fun saveUserId(id: String) {
        return localIdentityDataSource.saveUserId(id)
    }

    override suspend fun joinClub(clubId: Int, userId: Int): Club {
        return remoteDataSource.joinClub(clubId, userId).toEntity()
    }

    override fun getClubs(): List<Club> {
        return localIdentityDataSource.getClubs()
    }

    override suspend fun acceptJoiningRequest(clubId: Int, userId: Int, clubOwnerId: Int): Boolean {
        return remoteDataSource.acceptJoiningRequest(clubId, userId, clubOwnerId)
    }
}