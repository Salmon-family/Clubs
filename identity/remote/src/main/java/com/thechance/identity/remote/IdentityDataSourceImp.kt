package com.thechance.identity.remote

import android.util.Log
import com.thechance.identity.remote.response.IdentityBaseResponse
import com.thechance.identity.repositories.LocalIdentityDataSource
import com.thechance.identity.repositories.RemoteIdentityDataSource
import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.ClubDto
import com.thechance.identity.repositories.models.UserDTO
import com.thechance.identity.repositories.models.UserDataDTO
import retrofit2.Response
import javax.inject.Inject

class IdentityDataSourceImp @Inject constructor(
    private val service: IdentityService,
) : RemoteIdentityDataSource {

    override suspend fun login(userName: String, password: String): UserDTO {
        return wrap {
            service.login(userName, password)
        }
    }

    override suspend fun signup(userDataDTO: UserDataDTO): AccountDTO {
        return wrap {
            service.addUser(
                firstname = userDataDTO.firstname,
                lastname = userDataDTO.lastname,
                email = userDataDTO.email,
                reEmail = userDataDTO.email,
                gender = userDataDTO.gender,
                birthdate = userDataDTO.birthdate,
                username = userDataDTO.username,
                password = userDataDTO.password
            )
        }
    }

    override suspend fun joinClub(clubId: Int, userId: Int): ClubDto {
        return wrap { service.joinClub(clubId, userId) }
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