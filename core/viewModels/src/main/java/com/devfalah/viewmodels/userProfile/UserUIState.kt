package com.devfalah.viewmodels.userProfile

import android.graphics.Bitmap

data class UserUIState(
    val userDetails: UserDetailsUIState = UserDetailsUIState(),
    val albums: List<AlbumUIState> = emptyList(),
    val friends: List<FriendUIState> = emptyList(),
    val posts: List<PostUIState> = emptyList(),
    val isMyProfile: Boolean = true,
    val loading: Boolean = false,
    val majorError: String = "",
    val minorError: String = "",

    val bitmap: Bitmap? = null
)

data class UserDetailsUIState(
    val userID: Int = 0,
    val name: String = "",
    val userName: String = "",
    val title: String = "",
    val bio: String = "",
    val coverUrl: String = "",
    val profilePicture: String = "",
    val areFriends: Boolean = false,
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
) {
    companion object
}

data class FriendUIState(
    val id: Int = 0,
    val name: String = "",
    val profileImageUrl: String = ""
)