package com.thechance.usecase

import com.thechance.entity.User

interface Repository {

    suspend fun getUserDetails(): User?

}