package com.devfalah.viewmodels.userProfile.mapper

import com.devfalah.entities.User
import com.devfalah.viewmodels.userProfile.FriendUIState

fun List<User>.toFriendsUIState() = map { it.toFriendUIState() }

fun User.toFriendUIState(): FriendUIState {
    return FriendUIState(
        id = id,
        profileImageUrl = profileUrl,
        name = name
    )
}
