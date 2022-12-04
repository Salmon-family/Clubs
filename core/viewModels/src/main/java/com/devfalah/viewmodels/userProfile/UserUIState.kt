package com.devfalah.viewmodels.userProfile

import com.devfalah.entities.Album
import com.devfalah.entities.Post
import com.devfalah.entities.User

data class UserUIState(
    val userDetails: UserDetailsUIState = UserDetailsUIState(),
    val albums: List<AlbumUIState> = emptyList(),
    val friends: List<UserDetailsUIState> = emptyList(),
    val posts: List<PostUIState> = emptyList(),
    val isMyProfile: Boolean = true,
    val areFriends: Boolean = false,
    val loading: Boolean = false,
    val majorError: String = "",
    val minorError: String = ""
)

data class UserDetailsUIState(
    val userID: Int = 0,
    val name: String = "",
    val userName: String = "",
    val title: String = "",
    val description: String = "",
    val coverUrl: String = "",
    val profilePicture: String = ""
)

data class AlbumUIState(
    val albumID: Int = 0,
    val albumCover: String
)

data class PostUIState(
    val postId: Int = 0,
    val posterName: String = "",
    val posterImage: String = "",
    val privacy: Boolean = false,
    val createdData: String = "",
    val totalLikes: Int = 0,
    val totalComments: Int = 0,
    val isSaved: Boolean = false,
    val isLikedByUser: Boolean = false,
    val postImage: String = "",
    val postContent: String = "",
)

////// Mappers
fun User.toUIState(): UserDetailsUIState {
    return UserDetailsUIState(
        userID = userID,
        name = name,
        userName = username,
        title = title,
        description = description,
        coverUrl = coverUrl,
        profilePicture = icon.large
    )
}

fun Album.toUIState(): AlbumUIState {
    return AlbumUIState(
        albumID = albumID,
        albumCover = albumCoverPicture
    )
}

fun Post.toUIState(): PostUIState {
    return PostUIState(
        postId = postID,
        posterImage = posterImage,
        posterName = posterName,
        privacy = privacy,
        createdData = createdTime,
        totalLikes = totalLikes,
        totalComments = totalComments,
        isSaved = false,
        isLikedByUser = isLikedByUser,
        postImage = image,
        postContent = content
    )
}