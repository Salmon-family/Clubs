package com.devfalah.usecases

import com.devfalah.entities.User

class LoginUseCase (
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(userName: String,password: String):User{
        return authRepository.login(userName,password)
    }
}