package com.devfalah.viewmodels.friendRequest

import com.devfalah.entities.User

data class FriendRequestUiState(
    val friendRequests: List<UserState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class UserState(
    val userID: Int = 0,
    val name: String = "",
    val title: String = "",
    val profileImage: String = ""
)

fun User.toUserUIState(): UserState {
    return UserState(
        userID = userID,
        name = name,
        title = title,
        profileImage = icon.large
    )
}

fun List<User>.listToUserUiState() = map { user -> user.toUserUIState() }


