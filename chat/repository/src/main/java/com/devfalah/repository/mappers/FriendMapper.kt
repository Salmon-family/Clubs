package com.devfalah.repository.mappers

import com.devfalah.repository.models.FriendDTO
import com.thechance.entities.Friend

fun FriendDTO.toEntity(): Friend {
    return Friend(
        id = guid ?: 0,
        name = fullname ?: "",
        icon = icon?.large ?: "",
        title = jobTitle ?: ""
    )
}

fun List<FriendDTO>.toEntity(): List<Friend> = map { it.toEntity() }
