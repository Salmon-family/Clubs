package com.devfalah.usecases

import com.devfalah.entities.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(userName: String,password: String):User{
        return authRepository.login(userName,password)
    }
}