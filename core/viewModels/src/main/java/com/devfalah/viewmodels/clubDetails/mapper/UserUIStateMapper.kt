package com.devfalah.viewmodels.clubDetails.mapper

import com.devfalah.entities.User
import com.devfalah.viewmodels.clubDetails.MembersUIState

fun List<User>.toUIState() = map { it.toUIState() }

fun User.toUIState(): MembersUIState {
    return MembersUIState(
        id = id,
        name = name,
        title = title,
        profilePictureUrl = profileUrl,
    )
}
