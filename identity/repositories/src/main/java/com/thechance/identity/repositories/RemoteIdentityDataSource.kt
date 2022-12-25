package com.thechance.identity.repositories

import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.ClubDto
import com.thechance.identity.repositories.models.UserDTO
import com.thechance.identity.repositories.models.UserDataDTO

interface RemoteIdentityDataSource {

    suspend fun login(userName: String, password: String): UserDTO

    suspend fun signup(userDataDTO: UserDataDTO): String

    suspend fun joinClub(clubId: Int, userId: Int): ClubDto

    suspend fun acceptJoiningRequest(clubId: Int, userId: Int, clubOwnerId: Int): Boolean

    suspend fun updateFcmToken(userDataDto: UserDataDTO): UserDTO

}