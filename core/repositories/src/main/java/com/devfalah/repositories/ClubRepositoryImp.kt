package com.devfalah.repositories

import com.devfalah.entities.Notifications
import com.devfalah.entities.User
import com.devfalah.repositories.mappers.toEntity
import com.devfalah.usecases.ClubRepository
import javax.inject.Inject

class ClubRepositoryImp @Inject constructor (
    private val remoteDataSource: RemoteDataSource,
): ClubRepository {

    override suspend fun getUserFriends(userID: Int): List<User> {
        return remoteDataSource.getUserFriends(userID).map { it.toEntity() }
    }

    override suspend fun getNotifications(userID: Int): List<Notifications> {
        return remoteDataSource.getNotifications(userID).map { it.toEntity() }
    }

}