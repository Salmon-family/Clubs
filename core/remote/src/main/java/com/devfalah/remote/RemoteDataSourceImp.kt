package com.devfalah.remote

import com.devfalah.repositories.RemoteDataSource
import com.devfalah.repositories.models.UserDTO
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ClubService,
) : RemoteDataSource {
    override suspend fun login(userName: String, password: String): UserDTO {
        return apiService.login(userName,password).body()?.payload!!
    }
}