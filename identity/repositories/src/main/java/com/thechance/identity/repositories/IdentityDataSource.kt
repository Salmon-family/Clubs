package com.thechance.identity.repositories

import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.UserDTO
import com.thechance.identity.repositories.models.UserDataDTO

interface IdentityDataSource {

    suspend fun login(userName: String, password: String): UserDTO

    suspend fun signup(userDataDTO: UserDataDTO): AccountDTO
}