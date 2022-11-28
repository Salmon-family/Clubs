package com.devfalah.repository

import com.devfalah.repository.models.UserDTO

interface RemoteDataSource {
    suspend fun login(
        userName: String,
        password: String,
    ) : UserDTO
}