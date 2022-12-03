package com.devfalah.repositories

import com.devfalah.entities.Notifications
import com.devfalah.entities.User
import com.devfalah.repositories.mappers.toEntity
import com.devfalah.usecases.ClubRepository
import javax.inject.Inject

class ClubRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : ClubRepository {

    override suspend fun removeFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return remoteDataSource.removeFriendRequest(userID = userID, friendRequestID = friendRequestID)
    }

    override suspend fun addFriendRequest(userID: Int, friendRequestID: Int): Boolean {
        return remoteDataSource.addFriendRequest(userID = userID, friendRequestID = friendRequestID)
    }

    override suspend fun getUserFriendRequests(userID: Int): List<User> {
        return remoteDataSource.getUserFriendRequests(userID = userID).map { it.toEntity() }
    }

    override suspend fun getUserFriends(userID: Int): List<User> {
        return remoteDataSource.getUserFriends(userID).map { it.toEntity() }
    }

    override suspend fun getNotifications(userID: Int): List<Notifications> {
        return remoteDataSource.getNotifications(userID).map { it.toEntity() }
    }

}