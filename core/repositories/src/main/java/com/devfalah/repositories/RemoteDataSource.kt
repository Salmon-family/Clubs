package com.devfalah.repositories

import com.devfalah.repositories.models.UserDTO

interface RemoteDataSource {
    suspend fun login(
        userName: String,
        password: String,
    ) : UserDTO
}