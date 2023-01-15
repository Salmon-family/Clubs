package com.devfalah.viewmodels.userProfile

import com.devfalah.viewmodels.friends.FriendUIState
import com.devfalah.viewmodels.postDetails.PublishTimeUIState

data class UserUIState(
    val userDetails: UserDetailsUIState = UserDetailsUIState(),
    val friends: List<FriendUIState> = emptyList(),
    val posts: List<PostUIState> = emptyList(),
    val totalFriends: Int = 0,
    val loading: Boolean = false,
    val isPagerLoading: Boolean = false,
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
    val token: String = ""
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
    val isMyPost: Boolean = false,
    val privacy: Boolean = false,
    val createdData: PublishTimeUIState = PublishTimeUIState(),
    val createdDataValue: Long = 0L,
    val totalLikes: Int = 0,
    val totalComments: Int = 0,
    val isSaved: Boolean = false,
    val isLikedByUser: Boolean = false,
    val postImage: String = "",
    val postContent: String = "",
    val groupId: Int = 0,
    val isFromClub: Boolean = false,
    val groupName: String = "",
    val isFound: Boolean = true,
    val isFromAlbum: Boolean = false
)
