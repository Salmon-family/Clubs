package com.thechance.identity.repositories

import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.UserDTO

interface IdentityDataSource {

    suspend fun login(userName: String, password: String): Boolean //UserDTO

    suspend fun signup(
        firstname: String,
        lastname: String,
        email: String,
        reEmail: String,
        gender: String,
        birthdate: String,
        username: String,
        password: String
    ): AccountDTO
}