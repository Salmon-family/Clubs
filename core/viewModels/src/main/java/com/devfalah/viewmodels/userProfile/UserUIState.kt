package com.devfalah.viewmodels.userProfile

import com.devfalah.viewmodels.friends.FriendUIState

data class UserUIState(
    val id: Int = 0,
    val userDetails: UserDetailsUIState = UserDetailsUIState(),
    val friends: List<FriendUIState> = emptyList(),
    val posts: List<PostUIState> = emptyList(),
    val totalFriends: Int = 0,
    val isMyProfile: Boolean = true,
    val loading: Boolean = false,
    val isEndOfPager: Boolean = false,
    val majorError: String = "",
    val minorError: String = "",
)

data class UserDetailsUIState(
    val userID: Int = 0,
    val name: String = "",
    val userName: String = "",
    val title: String = "",
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
    val createdDataValue: Long = 0L,
    val totalLikes: Int = 0,
    val totalComments: Int = 0,
    val isSaved: Boolean = false,
    val isLikedByUser: Boolean = false,
    val postImage: String = "",
    val postContent: String = "",
    val groupId: Int = 0,
    val isFromClub: Boolean = false,
    val groupName: String = ""
)
