package com.devfalah.viewmodels.userProfile.mapper

import com.devfalah.entities.User
import com.devfalah.viewmodels.userProfile.UserDetailsUIState

fun List<User>.toUIState() = map { it.toUIState() }

fun User.toUIState(): UserDetailsUIState {
    return UserDetailsUIState(
        userID = id,
        name = name,
        userName = username,
        title = title,
        bio = bio,
        coverUrl = coverUrl,
        profilePicture = profileUrl,
        areFriends = isFriend,
        isMyProfile = isMyProfile,
        isRequestSend = isRequestExists
    )
}
