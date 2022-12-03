package com.devfalah.repositories

import com.devfalah.entities.Album
import com.devfalah.entities.Notifications
import com.devfalah.entities.Post
import com.devfalah.entities.User
import com.devfalah.repositories.mappers.toEntity
import com.devfalah.usecases.ClubRepository
import javax.inject.Inject

class ClubRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : ClubRepository {

    override suspend fun getUserFriends(userID: Int): List<User> {
        return remoteDataSource.getUserFriends(userID).map { it.toEntity() }
    }

    override suspend fun getNotifications(userID: Int): List<Notifications> {
        return remoteDataSource.getNotifications(userID).map { it.toEntity() }
    }

    override suspend fun getUserAccountDetails(userID: Int): User {
        return remoteDataSource.getUserAccountDetails(userID).toEntity()
    }

    override suspend fun getUserAlbums(userID: Int, albumID: Int): List<Album> {
        return remoteDataSource.getUserAlbums(userID, albumID).map { it.toEntity() }
    }

    override suspend fun getProfilePosts(userID: Int, profileUserID: Int): List<Post> {
        return remoteDataSource.getProfilePosts(userID, profileUserID).map { it.toEntity() }
    }

}