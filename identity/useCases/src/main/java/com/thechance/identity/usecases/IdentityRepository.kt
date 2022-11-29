package com.thechance.identity.usecases

import com.thechance.identity.entities.User

interface IdentityRepository {

    suspend  fun login(userName: String, password: String) : User
}