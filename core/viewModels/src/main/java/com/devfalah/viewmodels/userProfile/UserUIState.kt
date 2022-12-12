package com.devfalah.viewmodels.userProfile

data class UserUIState(
    val userDetails: UserDetailsUIState = UserDetailsUIState(),
    val friends: List<FriendUIState> = emptyList(),
    val posts: List<PostUIState> = emptyList(),
    val isMyProfile: Boolean = true,
    val loading: Boolean = false,
    val majorError: String = "",
    val minorError: String = "",
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
    val isMyProfile: Boolean = false,
    val isRequestSend: Boolean = false,
)

data class AlbumUIState(
    val albumID: Int = 0,
    val albumCover: String
)


data class PostUIState(
    val postId: Int = 0,
    val publisherName: String = "",
    val publisherImage: String = "",
    val publisherId: Int = 0,
    val privacy: Boolean = false,
    val createdData: String = "",
    val totalLikes: Int = 0,
    val totalComments: Int = 0,
    val isSaved: Boolean = false,
    val isLikedByUser: Boolean = false,
    val postImage: String = "",
    val postContent: String = "",
)

data class FriendUIState(
    val id: Int = 0,
    val name: String = "",
    val profileImageUrl: String = ""
)