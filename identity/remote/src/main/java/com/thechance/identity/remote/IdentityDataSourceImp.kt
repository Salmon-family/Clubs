package com.thechance.identity.remote

import com.thechance.identity.remote.response.IdentityBaseResponse
import com.thechance.identity.repositories.LocalIdentityDataSource
import com.thechance.identity.repositories.RemoteIdentityDataSource
import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.UserDTO
import retrofit2.Response
import javax.inject.Inject

class IdentityDataSourceImp @Inject constructor(
    private val service: IdentityService,
) : RemoteIdentityDataSource{

    override suspend fun login(userName: String, password: String): UserDTO {
        return wrap {
            service.login(userName, password)
        }
    }

    override suspend fun signup(
        firstname: String, lastname: String, email: String, reEmail: String,
        gender: String, birthdate: String, username: String, password: String
    ): AccountDTO {
        return wrap {
            service.addUser(
                firstname = firstname,
                lastname = lastname,
                email = email,
                reEmail = reEmail,
                gender = gender,
                birthdate = birthdate,
                username = username,
                password = password
            )
        }
    }


    private inline fun <reified I> wrap(
        function: () -> Response<IdentityBaseResponse<I>>
    ): I {
        val response = function()
        return if (response.isSuccessful) {
            when (response.body()!!.code) {
                "100" -> {
                    response.body()!!.payload
                }
                else -> throw Throwable(response.body()!!.message)
            } ?: throw Throwable(response.message().toString())
        } else {
            throw Throwable(" Not Success Request ")
        }
    }

}