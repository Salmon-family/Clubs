package com.thechance.repository

import com.thechance.repository.domainModel.UserDTO

interface RemoteDataSource {
    suspend fun getUserDetails(): UserDTO?

}