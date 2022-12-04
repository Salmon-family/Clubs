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
    ): Any? {
        val response = function()
        return if (response.isSuccessful) {
            when (response.body()!!.code) {
                "100" -> response.body()!!.payload
                "103" -> response.body()!!.message.toString()
                else -> throw Throwable("One or more component required for this request can not be found on remote server")
            }
        } else {
            throw Throwable(" Not Success Request ")
        }
    }
}