package com.devfalah.viewmodels.friends

data class FriendsUIState(
    val friends: List<FriendUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val pagerError: String = "",
)

data class FriendUIState(
    val id: Int = 0,
    val profilePictureUrl: String = "",
    val name: String = "",
    val title: String
)