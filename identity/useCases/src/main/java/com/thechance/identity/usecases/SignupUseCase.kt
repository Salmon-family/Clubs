package com.thechance.identity.usecases

import com.thechance.identity.entities.Account
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {

    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        reEmail: String,
        gender: String,
        birthdate: String,
        username: String,
        password: String
    ): Account {
        return identityRepository.signup(
            firstname = firstName, lastname = lastName, email = email, reEmail = reEmail,
            gender = gender, birthdate = birthdate, username = username, password = password
        )
    }
}