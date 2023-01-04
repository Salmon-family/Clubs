package com.devfalah.viewmodels.friends

import com.devfalah.entities.User

fun User.toUIState(): FriendUIState {
    return FriendUIState(
        id = id,
        profilePictureUrl = profileUrl,
        name = name,
        title = title,
    )
}

fun List<User>.toUIState() = map { it.toUIState() }