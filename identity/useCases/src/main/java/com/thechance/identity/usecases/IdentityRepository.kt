package com.thechance.identity.usecases

import com.thechance.identity.entities.Account
import com.thechance.identity.entities.User

interface IdentityRepository {

    suspend fun getFirstInstallValue(): Boolean

    suspend fun saveFirstInstallValue(value: Boolean): Boolean

    suspend fun login(userName: String, password: String): User

    suspend fun signup(
        firstname: String,
        lastname: String,
        email: String,
        reEmail: String,
        gender: String,
        birthdate: String,
        username: String,
        password: String
    ): Account
}