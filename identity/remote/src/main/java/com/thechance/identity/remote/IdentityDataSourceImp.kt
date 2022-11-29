package com.thechance.identity.remote

import com.thechance.identity.repositories.IdentityDataSource
import com.thechance.identity.repositories.models.UserDTO
import javax.inject.Inject

class IdentityDataSourceImp @Inject constructor(
    private val service: IdentityService
) : IdentityDataSource {

    override suspend fun login(userName: String, password: String): UserDTO {
        return service.login(userName, password).body()?.payload!!
    }
}