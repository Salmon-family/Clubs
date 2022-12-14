package com.thechance.identity.remote

import android.util.Log
import com.thechance.identity.remote.response.IdentityBaseResponse
import com.thechance.identity.repositories.LocalIdentityDataSource
import com.thechance.identity.repositories.RemoteIdentityDataSource
import com.thechance.identity.repositories.models.AccountDTO
import com.thechance.identity.repositories.models.UserDTO
import com.thechance.identity.repositories.models.UserDataDTO
import retrofit2.Response
import javax.inject.Inject

class IdentityDataSourceImp @Inject constructor(
    private val service: IdentityService,
) : RemoteIdentityDataSource {

    override suspend fun login(userName: String, password: String): UserDTO {
        Log.i("lllllllllllll1", userName.toString())
        Log.i("lllllllllllll2", password.toString())
        return wrap {
            service.login(userName, password)
        }
    }

    override suspend fun signup(userDataDTO: UserDataDTO): AccountDTO {
        Log.i("lllllllllllll1", userDataDTO.firstname.toString())
        Log.i("lllllllllllll2", userDataDTO.lastname.toString())
        Log.i("lllllllllllll3", userDataDTO.email.toString())
        Log.i("lllllllllllll4", userDataDTO.gender.toString())
        Log.i("lllllllllllll5", userDataDTO.birthdate.toString())
        Log.i("lllllllllllll6", userDataDTO.username.toString())
        Log.i("lllllllllllll7", userDataDTO.password.toString())

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