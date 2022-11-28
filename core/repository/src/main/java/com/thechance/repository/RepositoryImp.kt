package com.thechance.repository

import com.thechance.entity.User
import com.thechance.repository.mapper.map
import com.thechance.usecase.Repository

class RepositoryImp(private val dataSource: RemoteDataSource) : Repository {

    override suspend fun getUserDetails(userID: Int): User? {
        return dataSource.getUserDetails(userID)?.map()
    }

}