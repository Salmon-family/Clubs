package com.devfalah.viewmodels.friends

import com.devfalah.entities.User

data class FriendsUIState(
    val id: Int = 0,
    val friends: List<FriendUIState> = emptyList(),
    val totalFriends: Int = 0,
    val isMyProfile: Boolean = false,
    val isLoading: Boolean = false,
    val isPagerLoading:Boolean = false,
    val isPagerEnd: Boolean = false,
    val error: String = "",
    val minorError: String = ""
)

data class FriendUIState(
    val id: Int = 0,
    val profilePictureUrl: String = "",
    val name: String = "",
    val title: String
)

fun List<User>.toFriendsUIState() = map { it.toFriendUIState() }

fun User.toFriendUIState(): FriendUIState {
    return FriendUIState(
        id = id,
        profilePictureUrl = profileUrl,
        name = name,
        title = title
    )
}
