package com.devfalah.viewmodels.friendRequest

import com.devfalah.entities.User

data class FriendRequestUiState(
    val friendRequests: List<UserState>,
    val isLoading: Boolean = false
)

data class UserState(
    val userID: Int = 0,
    val name: String = "",
    val title: String = "",
    val profileImage: String = ""
)

fun User.toUIState(): UserState {
    return UserState(
        userID = userID,
        name = name,
        title = title,
        profileImage = icon.large
    )
}


