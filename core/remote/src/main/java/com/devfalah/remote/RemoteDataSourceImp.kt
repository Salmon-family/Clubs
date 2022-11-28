package com.devfalah.remote

import android.util.Log
import com.devfalah.repository.RemoteDataSource
import com.devfalah.repository.models.UserDTO
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ClubService,
) : RemoteDataSource {
    override suspend fun login(userName: String, password: String): UserDTO {
        Log.e("DEVFALAH",apiService.login(userName,password).toString())
        return apiService.login(userName,password).body()?.payload!!
    }
}