package com.devfalah.viewmodels.friends

data class FriendsUIState(
    val id: Int = 0,
    val friends: List<FriendUIState> = emptyList(),
    val isMyProfile: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
    val minorError: String = ""
)

data class FriendUIState(
    val id: Int = 0,
    val profilePictureUrl: String = "",
    val name: String = "",
    val title: String
)