package com.thechance.identity.usecases

import com.thechance.identity.entities.Club
import com.thechance.identity.entities.User
import com.thechance.identity.entities.UserData
import kotlinx.coroutines.flow.Flow

interface IdentityRepository {

    fun getStartInstallState(): Boolean?

    suspend fun setStartInstallState(value: Boolean)

    suspend fun login(userName: String, password: String): User

    suspend fun signup(userData: UserData): String

    fun getUserId(): String?

    suspend fun saveUserId(id: String)

    suspend fun joinClub(clubId: Int, userId: Int): Club

    fun getClubs(): List<Club>

    suspend fun acceptJoiningRequest(clubId: Int, userId: Int, clubOwnerId: Int): Boolean

    suspend fun getToken(): String

    suspend fun updateFcmToken(userData: UserData): User

    fun clearTables()

}