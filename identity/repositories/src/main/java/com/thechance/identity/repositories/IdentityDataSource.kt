package com.thechance.identity.repositories

import com.thechance.identity.repositories.models.UserDTO

interface IdentityDataSource {

    suspend fun login(userName: String, password: String): UserDTO

}