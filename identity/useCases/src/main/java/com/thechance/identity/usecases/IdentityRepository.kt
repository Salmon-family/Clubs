package com.thechance.identity.usecases

import com.thechance.identity.entities.Account
import com.thechance.identity.entities.User
import com.thechance.identity.entities.UserData

interface IdentityRepository {

    fun getStartInstallState(): Boolean?

    suspend fun setStartInstallState(value: Boolean)

    suspend fun login(userName: String, password: String): User

    suspend fun signup(userData: UserData): Account
}