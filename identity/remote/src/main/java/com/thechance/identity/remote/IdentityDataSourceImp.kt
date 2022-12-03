package com.thechance.identity.remote

import com.thechance.identity.remote.response.IdentityBaseResponse
import com.thechance.identity.repositories.IdentityDataSource
import com.thechance.identity.repositories.models.UserDTO
import retrofit2.Response
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
    ): Boolean {
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
        } != null
    }


    private suspend fun <I : Any> wrap(
        function: suspend () -> Response<IdentityBaseResponse<I>>
    ): I? {
        val response = function()
        return if (response.isSuccessful
            && response.body() != null
        ) {
            when (response.body()!!.code) {
                "100" -> response.body()!!.payload
                "101" -> throw Throwable("The requested method did not returned any response.")
                "104" -> throw Throwable("User not validated")
                else -> null
            }

        } else {
            throw Throwable(" Not Success Request ")
        }
    }
}