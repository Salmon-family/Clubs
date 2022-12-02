package com.thechance.identity.remote

import com.thechance.identity.repositories.IdentityDataSource
import com.thechance.identity.repositories.models.UserDTO
import javax.inject.Inject

class IdentityDataSourceImp @Inject constructor(
    private val service: IdentityService
) : IdentityDataSource {

    override suspend fun login(userName: String, password: String): UserDTO {
        return service.login(userName, password).body()?.payload
            ?: throw Throwable("failure request")
    }

    override suspend fun signup(
        firstname: String,
        lastname: String,
        email: String,
        reEmail: String,
        gender: String,
        birthdate: String,
        username: String,
        password: String
    ): UserDTO {
        return service.addUser(
            firstname = firstname,
            lastname = lastname,
            email = email,
            reEmail = reEmail,
            gender = gender,
            birthdate = birthdate,
            username = username,
            password = password
        ).body()?.payload ?: throw Throwable("failure request")
    }
}