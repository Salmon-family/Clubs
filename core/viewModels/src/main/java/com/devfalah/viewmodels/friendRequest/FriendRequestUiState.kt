package com.devfalah.viewmodels.friendRequest

import com.devfalah.entities.User

data class FriendRequestUiState(
    val friendRequests: List<UserState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val minorError: String = ""

)

data class UserState(
    val userID: Int = 0,
    val name: String = "",
    val title: String = "",
    val profileImage: String = "",
    val token: String = ""
)

fun User.toUserUIState(): UserState {
    return UserState(
        userID = id,
        name = name,
        title = title,
        profileImage = profileUrl,
        token = token
    )
}

fun List<User>.listToUserUiState() = map { user -> user.toUserUIState() }


