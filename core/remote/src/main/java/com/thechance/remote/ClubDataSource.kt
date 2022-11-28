package com.thechance.remote

import android.util.Log
import com.thechance.repository.RemoteDataSource
import com.thechance.repository.domainModel.UserDTO
import javax.inject.Inject

class ClubDataSource @Inject constructor(private val service: ClubService) : RemoteDataSource {

    override suspend fun getUserDetails(): UserDTO? {
        val x = service.getUserDetails(6).body()
        return x?.payload
    }
}