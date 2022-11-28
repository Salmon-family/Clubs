package com.devfalah.usecases

import com.devfalah.entities.User

interface AuthRepository {
    suspend  fun login(
        userName: String,
        password: String,
    ) : User
}