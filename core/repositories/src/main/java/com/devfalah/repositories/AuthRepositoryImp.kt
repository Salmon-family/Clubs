package com.devfalah.repositories

import com.devfalah.entities.User
import com.devfalah.repositories.mappers.toUser
import com.devfalah.usecases.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor (
    private val remoteDataSource: RemoteDataSource,
): AuthRepository {
    override suspend fun login(userName: String, password: String): User {
        return remoteDataSource.login(userName,password).toUser()
    }
}