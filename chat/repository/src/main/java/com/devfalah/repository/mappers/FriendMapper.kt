package com.devfalah.repository.mappers

import com.devfalah.repository.models.FriendDTO
import com.devfalah.repository.models.FriendsResponse
import com.thechance.entities.Friend
import com.thechance.entities.Friends

fun FriendDTO.toEntity(): Friend {
    return Friend(
        id = guid ?: 0,
        name = fullname ?: "",
        icon = icon?.large ?: "",
        title = jobTitle ?: ""
    )
}

fun List<FriendDTO>.toEntity(): List<Friend> = map { it.toEntity() }

fun FriendsResponse.toEntity(): Friends {
    return Friends(
        friends = list?.toEntity() ?: emptyList(),
        total = total ?: 0,
        page = offset ?: 0
    )
}
