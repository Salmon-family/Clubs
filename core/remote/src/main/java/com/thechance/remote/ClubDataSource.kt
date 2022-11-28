package com.thechance.remote

import com.thechance.repository.RemoteDataSource
import com.thechance.repository.domainModel.UserDTO
import javax.inject.Inject

class ClubDataSource @Inject constructor(private val service: ClubService) : RemoteDataSource {

    override suspend fun getUserDetails(userID: Int): UserDTO? {
        return service.getUserDetails(userID).body()?.payload
    }


}